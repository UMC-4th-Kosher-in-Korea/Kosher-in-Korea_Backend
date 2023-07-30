package com.kusher.kusher_in_korea.auth.controller;

import com.kusher.kusher_in_korea.reviewfeedback.dto.ReviewDto;
import com.kusher.kusher_in_korea.reviewfeedback.service.ReviewService;
import com.kusher.kusher_in_korea.tabling.dto.response.ReservationDto;
import com.kusher.kusher_in_korea.auth.dto.UserDto;
import com.kusher.kusher_in_korea.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;

    // 유저 추가
    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserDto userDto) {
        Long userId = userService.createUser(userDto);
        return ResponseEntity.ok(userId);
    }

    // 유저 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }

    // 유저 정보 수정
    @PutMapping("/{userId}")
    public ResponseEntity<Long> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        Long updatedUserId = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(updatedUserId);
    }

    // 유저의 예약 조회
    @GetMapping("/{userId}/reservations")
    public ResponseEntity<List<ReservationDto>> getUserReservations(@PathVariable Long userId) {
        List<ReservationDto> reservationDtos = userService.getUserReservation(userId);
        return ResponseEntity.ok(reservationDtos);
    }

    // 유저가 남긴 리뷰 조회
    @GetMapping("/review")
    public ResponseEntity<List<ReviewDto>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewDto> reviews = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviews);
    }
}
