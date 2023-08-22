package com.kusher.kusher_in_korea.tabling.dto.response;

import com.kusher.kusher_in_korea.tabling.domain.RestaurantMenu;
import lombok.Getter;

@Getter
public class RestaurantMenuDto {

    private Long id; // 메뉴번호
    private Long restaurantId; // 식당번호
    private String menuName; // 메뉴이름
    private int price; // 메뉴가격
    private String menuImage; // 메뉴이미지
    private String menuDescription; // 메뉴설명

    public RestaurantMenuDto(RestaurantMenu restaurantMenu) {
        this.id = restaurantMenu.getId();
        this.restaurantId = restaurantMenu.getRestaurant().getId();
        this.menuName = restaurantMenu.getMenuName();
        this.price = restaurantMenu.getPrice();
        this.menuImage = restaurantMenu.getMenuImage();
        this.menuDescription = restaurantMenu.getMenuDescription();
    }
}
