package com.kusher.kusher_in_korea.tabling.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "restaurant_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_menu_id")
    private Long id; // 메뉴번호

    @ManyToOne(fetch = FetchType.LAZY) // 메뉴와 식당은 다대일 관계
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant; // 식당

    private String menuName; // 메뉴이름

    private int Price; // 메뉴가격

    private String menuImage; // 메뉴이미지

    private String menuDescription; // 메뉴설명

    public static RestaurantMenu createRestaurantMenu(String menuName, int Price, String menuDescription, String menuImage) {
        RestaurantMenu restaurantMenu = new RestaurantMenu();
        restaurantMenu.menuName = menuName;
        restaurantMenu.Price = Price;
        restaurantMenu.menuImage = menuImage;
        restaurantMenu.menuDescription = menuDescription;
        return restaurantMenu;
    }

    public void updateRestaurantMenu(String menuName, int Price, String menuDescription) {
        this.menuName = menuName;
        this.Price = Price;
        this.menuDescription = menuDescription;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
