package com.kusher.kusher_in_korea.tabling.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurant")
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id; // 식당번호

    private Long ownerId; // 식당주인번호

    private String location; // 식당주소

    private String restaurantName; // 식당이름

    private String restaurantPhone; // 식당전화번호

    private Long capacity; // 최대수용인원

    private LocalDateTime openTime; // 오픈시간

    private LocalDateTime closeTime; // 마감시간

    private String description; // 식당설명

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantMenu> restaurantMenus = new ArrayList<>(); // 식당메뉴

    // 생성 메서드
    public static Restaurant createRestaurant(Long ownerId, String location, String restaurantName, String restaurantPhone, Long capacity, LocalDateTime openTime, LocalDateTime closeTime, String description) {
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(ownerId);
        restaurant.setLocation(location);
        restaurant.setRestaurantName(restaurantName);
        restaurant.setRestaurantPhone(restaurantPhone);
        restaurant.setCapacity(capacity);
        restaurant.setOpenTime(openTime);
        restaurant.setCloseTime(closeTime);
        restaurant.setDescription(description);
        return restaurant;
    }

    // 비즈니스 로직
    // 식당정보 수정
    public void updateRestaurant(String location, String restaurantName, String restaurantPhone, Long capacity, LocalDateTime openTime, LocalDateTime closeTime, String description) {
        this.location = location;
        this.restaurantName = restaurantName;
        this.restaurantPhone = restaurantPhone;
        this.capacity = capacity;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.description = description;
    }

    // 식당에 메뉴 추가
    public void addRestaurantMenu(RestaurantMenu restaurantMenu) {
        restaurantMenu.setRestaurant(this);
        this.getRestaurantMenus().add(restaurantMenu);
    }

    // 식당에 메뉴 수정
    public void updateRestaurantMenu(RestaurantMenu restaurantMenu, String menuName, Long price, String menuDescription) {
        restaurantMenu.setMenuName(menuName);
        restaurantMenu.setPrice(price);
        restaurantMenu.setMenuDescription(menuDescription);
    }

    // 식당에 메뉴 삭제
    public void deleteRestaurantMenu(RestaurantMenu restaurantMenu) {
        this.getRestaurantMenus().remove(restaurantMenu);
    }
}
