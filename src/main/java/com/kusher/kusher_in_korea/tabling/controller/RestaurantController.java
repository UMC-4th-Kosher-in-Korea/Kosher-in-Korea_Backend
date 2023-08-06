package com.kusher.kusher_in_korea.tabling.controller;

import com.kusher.kusher_in_korea.reviewfeedback.dto.ReviewDto;
import com.kusher.kusher_in_korea.reviewfeedback.service.ReviewService;
import com.kusher.kusher_in_korea.tabling.dto.request.*;
import com.kusher.kusher_in_korea.tabling.dto.response.RestaurantDto;
import com.kusher.kusher_in_korea.tabling.dto.response.RestaurantMenuDto;
import com.kusher.kusher_in_korea.tabling.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    // 모든 식당 조회
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> restaurants = restaurantService.findAllRestaurant();
        return ResponseEntity.ok(restaurants);
    }

    // 특정 식당 조회
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long restaurantId) {
        Optional<RestaurantDto> restaurantDto = restaurantService.findRestaurant(restaurantId);
        return restaurantDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 특정 식당 메뉴 조회
    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<List<RestaurantMenuDto>> getRestaurantMenus(@PathVariable Long restaurantId) {
        List<RestaurantMenuDto> menuDtos = restaurantService.findRestaurantMenu(restaurantId);
        return ResponseEntity.ok(menuDtos);
    }

    // 새 식당 추가 (점주 타입만 가능)
    @PostMapping
    public ResponseEntity<Long> addNewRestaurant(@RequestBody CreateRestaurantDto createRestaurantDto) {
        Long restaurantId = restaurantService.saveRestaurant(createRestaurantDto);
        return ResponseEntity.ok(restaurantId);
    }

    // 특정 식당 정보 수정
    @PutMapping("/{restaurantId}")
    public ResponseEntity<Long> updateRestaurantInfo(@PathVariable Long restaurantId, @RequestBody UpdateRestaurantDto restaurantDto) {
        Long updatedRestaurantId = restaurantService.updateRestaurant(restaurantId, restaurantDto);
        return ResponseEntity.ok(updatedRestaurantId);
    }

    // 특정 식당 메뉴 추가
    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<Long> addNewRestaurantMenu(@PathVariable Long restaurantId, @RequestBody CreateRestaurantMenuDto createRestaurantMenuDto) {
        Long menuId = restaurantService.saveRestaurantMenu(restaurantId, createRestaurantMenuDto);
        return ResponseEntity.ok(menuId);
    }

    // 특정 식당 메뉴 수정
    @PutMapping("/{restaurantId}/menu/{menuId}")
    public ResponseEntity<Long> updateRestaurantMenu(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody UpdateRestaurantMenuDto restaurantMenuDto) {
        restaurantService.updateRestaurantMenu(restaurantId, menuId, restaurantMenuDto);
        return ResponseEntity.noContent().build();
    }

    // 특정 식당 메뉴 삭제
    @DeleteMapping("/{restaurantId}/menu/{menuId}")
    public ResponseEntity<Long> deleteRestaurantMenu(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody DeleteRestaurantMenuDto deleteRestaurantMenuDto) {
        restaurantService.deleteRestaurantMenu(restaurantId, menuId, deleteRestaurantMenuDto);
        return ResponseEntity.noContent().build();
    }

    // 특정 식당에 대한 리뷰 조회
    @GetMapping("/{restaurantId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviewsByRestaurantId(@PathVariable Long restaurantId) {
        List<ReviewDto> reviews = reviewService.getReviewsByRestaurantId(restaurantId);
        return ResponseEntity.ok(reviews);
    }
}
