package com.kusher.kusher_in_korea.tabling.domain;

import com.kusher.kusher_in_korea.tabling.dto.request.CreateRestaurantDto;
import com.kusher.kusher_in_korea.tabling.dto.request.CreateRestaurantMenuDto;
import com.kusher.kusher_in_korea.tabling.dto.request.UpdateRestaurantDto;
import com.kusher.kusher_in_korea.tabling.dto.request.UpdateRestaurantMenuDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "restaurant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id; // 식당번호

    private Long ownerId; // 식당주인번호

    private String location; // 식당주소

    private String restaurantName; // 식당이름

    private String restaurantPhone; // 식당전화번호

    private int capacity; // 최대수용인원

    private LocalTime openTime; // 오픈시간

    private LocalTime closeTime; // 마감시간

    private String description; // 식당설명

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<RestaurantMenu> restaurantMenus = new ArrayList<>(); // 식당메뉴

    // 생성 메서드
    public static Restaurant createRestaurant(CreateRestaurantDto createRestaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.ownerId = createRestaurantDto.getUserId();
        restaurant.location = createRestaurantDto.getLocation();
        restaurant.restaurantName = createRestaurantDto.getRestaurantName();
        restaurant.restaurantPhone = createRestaurantDto.getRestaurantPhone();
        restaurant.capacity = createRestaurantDto.getCapacity();
        restaurant.openTime = createRestaurantDto.getOpenTime();
        restaurant.closeTime = createRestaurantDto.getCloseTime();
        restaurant.description = createRestaurantDto.getDescription();
        return restaurant;
    }

    // 비즈니스 로직
    // 식당정보 수정
    public void updateRestaurant(UpdateRestaurantDto updateRestaurantDto) {
        this.ownerId = updateRestaurantDto.getUserId();
        this.location = updateRestaurantDto.getLocation();
        this.restaurantName = updateRestaurantDto.getRestaurantName();
        this.restaurantPhone = updateRestaurantDto.getRestaurantPhone();
        this.capacity = updateRestaurantDto.getCapacity();
        this.openTime = updateRestaurantDto.getOpenTime();
        this.closeTime = updateRestaurantDto.getCloseTime();
        this.description = updateRestaurantDto.getDescription();
    }

    // 식당에 메뉴 추가
    public void addRestaurantMenu(CreateRestaurantMenuDto restaurantMenu, String menuImage) {
        RestaurantMenu newMenu = RestaurantMenu.createRestaurantMenu(restaurantMenu.getMenuName(), restaurantMenu.getPrice(), restaurantMenu.getMenuDescription(), menuImage);
        newMenu.setRestaurant(this);
        this.getRestaurantMenus().add(newMenu);
    }

    // 식당에 메뉴 수정
    public void updateRestaurantMenu(Long menuId, UpdateRestaurantMenuDto restaurantMenu) {
        for (RestaurantMenu menu : this.getRestaurantMenus()) {
            if (menu.getId().equals(menuId)) {
                menu.updateRestaurantMenu(restaurantMenu.getMenuName(), restaurantMenu.getPrice(), restaurantMenu.getDescription());
                break;
            }
        }
    }

    // 식당에 메뉴 삭제
    public void deleteRestaurantMenu(Long menuId) {
        for (RestaurantMenu menu : this.getRestaurantMenus()) {
            if (menu.getId().equals(menuId)) {
                this.getRestaurantMenus().remove(menu);
                menu.setRestaurant(null); // 이제 이 메뉴는 식당에 속하지 않음
                break;
            }
        }
    }

    //최대수용인원 확인
    public boolean isAvailableVisitorCount(int totalCount, int requestCount) {
        return this.capacity - totalCount >= requestCount;
    }
}
