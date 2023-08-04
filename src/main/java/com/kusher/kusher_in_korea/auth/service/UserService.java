package com.kusher.kusher_in_korea.auth.service;

import com.kusher.kusher_in_korea.ingredient.domain.Cart;
import com.kusher.kusher_in_korea.ingredient.dto.response.CartDto;
import com.kusher.kusher_in_korea.tabling.domain.Reservation;
import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.tabling.dto.response.ReservationDto;
import com.kusher.kusher_in_korea.auth.dto.UserDto;
import com.kusher.kusher_in_korea.tabling.repository.ReservationRepository;
import com.kusher.kusher_in_korea.auth.repository.UserRepository;
import com.kusher.kusher_in_korea.util.exception.CustomException;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    // 유저 추가
    public Long createUser(UserDto userDto) {
        ValidationDuplicateUser(userDto);
        User user = User.createUser(userDto);
        Cart cart = Cart.createCart(user); // 유저 생성 시 유저가 사용할 장바구니도 생성
        return userRepository.save(user).getId();
    }

    // 유저명 중복 방지
    public void ValidationDuplicateUser(UserDto userDto) {
        List<User> findUsers = userRepository.findByUserName(userDto.getUserName());
        if (!findUsers.isEmpty()) {
            throw new CustomException(ResponseCode.DUPLICATED_USER);
        }
    }

    // 유저 정보 조회
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        return new UserDto(user);
    }

    // 유저 정보 수정
    public Long updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        user.updateUser(userDto);
        return userId;
    }

    // 유저의 예약 조회
    public List<ReservationDto> getUserReservation(Long userId) {
        List<Reservation> reservations = reservationRepository.findAllByUserId(userId);
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDtos.add(new ReservationDto(reservation));
        }
        return reservationDtos;
    }

    // 유저의 장바구니 조회
    public CartDto getUserCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        return new CartDto(user.getCart());
    }

}
