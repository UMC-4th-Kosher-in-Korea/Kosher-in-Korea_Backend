package com.kusher.kusher_in_korea.tabling.service;

import com.kusher.kusher_in_korea.tabling.domain.Reservation;
import com.kusher.kusher_in_korea.tabling.domain.User;
import com.kusher.kusher_in_korea.tabling.dto.UserDto;
import com.kusher.kusher_in_korea.tabling.repository.RestaurantRepository;
import com.kusher.kusher_in_korea.tabling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    // 유저 추가
    public Long createUser(UserDto userDto) {
        ValidationDuplicateUser(userDto);
        User user = User.createUser(userDto);
        return userRepository.save(user).getId();
    }

    // 유저명 중복 방지
    public void ValidationDuplicateUser(UserDto userDto) {
        List<User> findUsers = userRepository.findByUserName(userDto.getUserName());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 유저입니다.");
        }
    }

    // 유저 정보 조회
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));
        return new UserDto(user);
    }

    // 유저 정보 수정
    public Long updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));
        user.updateUser(userDto);
        return userId;
    }

    // 유저의 예약 조회 (ReservationDto 구현 필요)
    /*private List<ReservationDto> getUserReservation(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));
        List<Reservation> reservations = user.getReservations();
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDtos.add(new ReservationDto(reservation));
        }
        return reservationDtos;
    }*/
}
