package com.kusher.kusher_in_korea.auth.controller;

import com.kusher.kusher_in_korea.auth.dto.RequestUserDto;
import com.kusher.kusher_in_korea.ingredient.dto.response.CartDto;
import com.kusher.kusher_in_korea.ingredient.dto.response.OrdersDto;
import com.kusher.kusher_in_korea.ingredient.service.OrdersService;
import com.kusher.kusher_in_korea.reviewfeedback.dto.ReviewDto;
import com.kusher.kusher_in_korea.reviewfeedback.service.ReviewService;
import com.kusher.kusher_in_korea.tabling.dto.response.ReservationDto;
import com.kusher.kusher_in_korea.auth.dto.UserDto;
import com.kusher.kusher_in_korea.auth.service.UserService;
import com.kusher.kusher_in_korea.util.api.ApiResponse;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final OrdersService ordersService;

    // 회원가입
    @PostMapping("/create")
    public ApiResponse<Long> createUser(@RequestBody RequestUserDto userDto) {
        Long userId = userService.createUser(userDto);
        return ApiResponse.success(userId, ResponseCode.USER_CREATE_SUCCESS.getMessage());
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<UserDto> login(@RequestBody RequestUserDto userDto) {
        UserDto loginUser = userService.login(userDto);
        return ApiResponse.success(loginUser, ResponseCode.LOGIN_SUCCESS.getMessage());
    }

    // 유저 정보 조회
    @GetMapping("/{userId}")
    public ApiResponse<UserDto> getUser(@PathVariable Long userId) {
        UserDto userDto = userService.getUser(userId);
        return ApiResponse.success(userDto, ResponseCode.USER_READ_SUCCESS.getMessage());
    }

    // 유저의 예약 조회
    @GetMapping("/{userId}/reservations")
    public ApiResponse<List<ReservationDto>> getUserReservations(@PathVariable Long userId) {
        List<ReservationDto> reservationDtos = userService.getUserReservation(userId);
        return ApiResponse.success(reservationDtos, ResponseCode.RESERVATION_READ_SUCCESS.getMessage());
    }

    // 유저의 주문 조회
    @GetMapping("/{userId}/orders")
    public ApiResponse<List<OrdersDto>> getUserOrders(@PathVariable Long userId) {
        List<OrdersDto> ordersDtos = ordersService.getUserOrders(userId);
        return ApiResponse.success(ordersDtos, ResponseCode.ORDERS_READ_SUCCESS.getMessage());
    }

    // 유저가 남긴 리뷰 조회
    @GetMapping("{userId}/review")
    public ApiResponse<List<ReviewDto>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewDto> reviews = reviewService.getReviewsByUserId(userId);
        return ApiResponse.success(reviews, ResponseCode.REVIEW_READ_SUCCESS.getMessage());
    }

    // 유저의 장바구니 조회
    @GetMapping("/{userId}/cart")
    public ApiResponse<CartDto> getUserCart(@PathVariable Long userId) {
        CartDto ordersDtos = userService.getUserCart(userId);
        return ApiResponse.success(ordersDtos, ResponseCode.CART_READ_SUCCESS.getMessage());
    }

}
