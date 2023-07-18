package com.kusher.kusher_in_korea.tabling.dto.response;


import com.kusher.kusher_in_korea.tabling.domain.Restaurant;
import com.kusher.kusher_in_korea.tabling.domain.RestaurantMenu;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RestaurantDto {

    private Long id; // 식당번호
    private Long userId; // 식당주인(유저번호)
    private String location; // 식당위치
    private String restaurantName; // 식당이름
    private String restaurantPhone; // 식당전화번호
    private Long capacity; // 최대수용인원
    private String openTime; // 오픈시간
    private String closeTime; // 마감시간
    private String description; // 식당설명
    private List<RestaurantMenuDto> restaurantMenus = new ArrayList<>(); // 식당메뉴

    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.userId = restaurant.getOwnerId();
        this.location = restaurant.getLocation();
        this.restaurantName = restaurant.getRestaurantName();
        this.restaurantPhone = restaurant.getRestaurantPhone();
        this.capacity = restaurant.getCapacity();
        this.openTime = restaurant.getOpenTime().toString();
        this.closeTime = restaurant.getCloseTime().toString();
        this.description = restaurant.getDescription();
        List<RestaurantMenu> menus = restaurant.getRestaurantMenus();
        for (RestaurantMenu menu : menus) {
            this.restaurantMenus.add(new RestaurantMenuDto(menu));
        }
    }
}
