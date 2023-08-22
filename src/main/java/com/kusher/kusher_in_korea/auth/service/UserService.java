package com.kusher.kusher_in_korea.auth.service;

import com.kusher.kusher_in_korea.auth.dto.CreateUserDto;
import com.kusher.kusher_in_korea.auth.dto.LoginDto;
import com.kusher.kusher_in_korea.auth.dto.UpdateUserDto;
import com.kusher.kusher_in_korea.ingredient.domain.Cart;
import com.kusher.kusher_in_korea.ingredient.dto.response.CartDto;
import com.kusher.kusher_in_korea.ingredient.repository.CartRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final CartRepository cartRepository;

    // 유저 추가
    @Transactional
    public Long createUser(CreateUserDto userDto, String imageUrl) {
        ValidationDuplicateUser(userDto.getUserEmail());
        User user = User.createUser(userDto.getUserName(), userDto.getUserEmail(), userDto.getUserPhone(), imageUrl, userDto.getUserAddress());
        Cart cart = Cart.createCart(user); // 유저 생성 시 유저가 사용할 장바구니도 생성
        Long id = userRepository.save(user).getId();
        cartRepository.save(cart);
        return id;
    }

    // 유저명 중복 방지
    public void ValidationDuplicateUser(String userEmail) {
        if (userRepository.existsByUserEmail(userEmail)) {
            throw new CustomException(ResponseCode.DUPLICATED_USER);
        }
    }

    // 로그인
    public UserDto login(LoginDto userDto) {
        User user = userRepository.findByUserEmail(userDto.getUserEmail()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        user.setNotFirstLogin(); // 이제 첫 로그인이 아님
        return new UserDto(user);
    }

    // 이 이메일로 로그인한 적이 있는지 유무 판정
    public boolean checkLogin(String userEmail) {
        Optional<User> findUsers = userRepository.findByUserEmail(userEmail);
        return findUsers.map(User::isFirstLogin).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
    }

    // 유저 정보 수정
    @Transactional
    public Long updateUser(Long userId, UpdateUserDto userDto, String imageUrl) {
        User user = getUserById(userId);
        user.updateUser(userDto.getUserName(), userDto.getUserPhone(), imageUrl, userDto.getUserAddress());
        return user.getId();
    }

    // 유저 정보 조회
    public UserDto getUser(Long userId) {
        User user = getUserById(userId);
        return new UserDto(user);
    }

    // 유저의 예약 조회
    public List<ReservationDto> getUserReservation(Long userId) {
        List<Reservation> reservations = reservationRepository.findAllByUserId(userId);
        return reservations.stream().map(ReservationDto::new).collect(Collectors.toList());
    }

    // 유저의 장바구니 조회
    public CartDto getUserCart(Long userId) {
        User user = getUserById(userId);
        return new CartDto(user.getCart());
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
    }

}
