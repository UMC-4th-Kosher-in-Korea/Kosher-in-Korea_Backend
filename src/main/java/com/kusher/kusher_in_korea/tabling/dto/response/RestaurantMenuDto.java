package com.kusher.kusher_in_korea.tabling.dto.response;

import com.kusher.kusher_in_korea.tabling.domain.RestaurantMenu;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestaurantMenuDto {
    private Long id; // 메뉴번호

    private Long restaurantId; // 식당번호

    private String menuName; // 메뉴이름

    private int price; // 메뉴가격

    private String menuDescription; // 메뉴설명

    public RestaurantMenuDto(RestaurantMenu restaurantMenu) {
        this.id = restaurantMenu.getId();
        this.restaurantId = restaurantMenu.getRestaurant().getId();
        this.menuName = restaurantMenu.getMenuName();
        this.price = restaurantMenu.getPrice();
        this.menuDescription = restaurantMenu.getMenuDescription();
    }
}
