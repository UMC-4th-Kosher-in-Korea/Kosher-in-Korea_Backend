package com.kusher.kusher_in_korea.tabling.controller;

import com.kusher.kusher_in_korea.image.service.ImageUploadService;
import com.kusher.kusher_in_korea.reviewfeedback.dto.ReviewDto;
import com.kusher.kusher_in_korea.reviewfeedback.service.ReviewService;
import com.kusher.kusher_in_korea.tabling.dto.request.*;
import com.kusher.kusher_in_korea.tabling.dto.response.ReservationDto;
import com.kusher.kusher_in_korea.tabling.dto.response.RestaurantDto;
import com.kusher.kusher_in_korea.tabling.dto.response.RestaurantMenuDto;
import com.kusher.kusher_in_korea.tabling.service.ReservationService;
import com.kusher.kusher_in_korea.tabling.service.RestaurantService;
import com.kusher.kusher_in_korea.util.api.ApiResponse;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;
    private final ReservationService reservationService;
    private final ImageUploadService imageUploadService;

    // 모든 식당 조회
    @GetMapping
    public ApiResponse<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> restaurants = restaurantService.findAllRestaurant();
        return ApiResponse.success(restaurants, ResponseCode.RESTAURANT_READ_SUCCESS.getMessage());
    }

    // 특정 식당 조회
    @GetMapping("/{restaurantId}")
    public ApiResponse<RestaurantDto> getRestaurantById(@PathVariable Long restaurantId) {
        RestaurantDto restaurantDto = restaurantService.findRestaurant(restaurantId);
        return ApiResponse.success(restaurantDto, ResponseCode.RESTAURANT_READ_SUCCESS.getMessage());
    }

    // 특정 식당 메뉴 조회
    @GetMapping("/{restaurantId}/menu")
    public ApiResponse<List<RestaurantMenuDto>> getRestaurantMenus(@PathVariable Long restaurantId) {
        List<RestaurantMenuDto> menuDtos = restaurantService.findRestaurantMenu(restaurantId);
        return ApiResponse.success(menuDtos, ResponseCode.RESTAURANT_MENU_READ_SUCCESS.getMessage());
    }

    // 새 식당 추가 (점주 타입만 가능)
    @PostMapping
    public ApiResponse<Long> addNewRestaurant(@RequestBody CreateRestaurantDto createRestaurantDto) {
        Long restaurantId = restaurantService.saveRestaurant(createRestaurantDto);
        return ApiResponse.success(restaurantId, ResponseCode.RESTAURANT_CREATE_SUCCESS.getMessage());
    }

    // 특정 식당 정보 수정
    @PutMapping("/{restaurantId}")
    public ApiResponse<Long> updateRestaurantInfo(@PathVariable Long restaurantId, @RequestBody UpdateRestaurantDto restaurantDto) {
        Long updatedRestaurantId = restaurantService.updateRestaurant(restaurantId, restaurantDto);
        return ApiResponse.success(updatedRestaurantId, ResponseCode.RESTAURANT_UPDATE_SUCCESS.getMessage());
    }

    // 특정 식당 메뉴 추가 (사진 필요)
    @PostMapping("/{restaurantId}/menu")
    public ApiResponse<Long> addNewRestaurantMenu(@PathVariable Long restaurantId, @RequestBody CreateRestaurantMenuDto createRestaurantMenuDto) throws IOException {
        String imageUrl = null; // 이미지가 없을 경우 null
        if (createRestaurantMenuDto.getMenuImage() != null) { // 이미지가 있을 경우
            imageUrl = imageUploadService.uploadImage(createRestaurantMenuDto.getMenuImage());
        }
        Long menuId = restaurantService.saveRestaurantMenu(restaurantId, createRestaurantMenuDto, imageUrl);
        return ApiResponse.success(menuId, ResponseCode.RESTAURANT_MENU_CREATE_SUCCESS.getMessage());
    }

    // 특정 식당 메뉴 수정
    @PutMapping("/{restaurantId}/menu/{menuId}")
    public ApiResponse<Long> updateRestaurantMenu(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody UpdateRestaurantMenuDto restaurantMenuDto) {
        restaurantService.updateRestaurantMenu(restaurantId, menuId, restaurantMenuDto);
        return ApiResponse.success(menuId, ResponseCode.RESTAURANT_MENU_UPDATE_SUCCESS.getMessage());
    }

    // 특정 식당 메뉴 삭제
    @DeleteMapping("/{restaurantId}/menu/{menuId}")
    public ApiResponse<Long> deleteRestaurantMenu(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody DeleteRestaurantMenuDto deleteRestaurantMenuDto) {
        restaurantService.deleteRestaurantMenu(restaurantId, menuId, deleteRestaurantMenuDto);
        return ApiResponse.success(menuId, ResponseCode.RESTAURANT_MENU_DELETE_SUCCESS.getMessage());
    }

    // 특정 식당에 대한 리뷰 조회
    @GetMapping("/{restaurantId}/reviews")
    public ApiResponse<List<ReviewDto>> getReviewsByRestaurantId(@PathVariable Long restaurantId) {
        List<ReviewDto> reviews = reviewService.getReviewsByRestaurantId(restaurantId);
        return ApiResponse.success(reviews, ResponseCode.REVIEW_READ_SUCCESS.getMessage());
    }

    // 특정 식당에 대한 예약 조회
    @GetMapping("/{restaurantId}/reservations")
    public ApiResponse<List<ReservationDto>> getReservationsByRestaurantId(@PathVariable Long restaurantId) {
        List<ReservationDto> reservations = reservationService.findReservationsByRestaurantId(restaurantId);
        return ApiResponse.success(reservations, ResponseCode.RESERVATION_READ_SUCCESS.getMessage());
    }
}
