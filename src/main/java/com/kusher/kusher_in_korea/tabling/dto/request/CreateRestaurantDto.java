package com.kusher.kusher_in_korea.tabling.dto.request;

import com.kusher.kusher_in_korea.tabling.domain.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateRestaurantDto { // 식당 추가 요청

    private Long userId; // 식당주인(유저번호)
    private String location; // 식당위치
    private String restaurantName; // 식당이름
    private String restaurantPhone; // 식당전화번호
    private Long capacity; // 최대수용인원
    private String openTime; // 오픈시간
    private String closeTime; // 마감시간
    private String description; // 식당설명
    private List<CreateRestaurantMenuDto> restaurantMenus = new ArrayList<>(); // 식당메뉴

    public CreateRestaurantDto(Restaurant restaurant) {
        this.userId = restaurant.getOwnerId();
        this.location = restaurant.getLocation();
        this.restaurantName = restaurant.getRestaurantName();
        this.restaurantPhone = restaurant.getRestaurantPhone();
        this.capacity = restaurant.getCapacity();
        this.openTime = restaurant.getOpenTime().toString();
        this.closeTime = restaurant.getCloseTime().toString();
        this.description = restaurant.getDescription();
    }
}
