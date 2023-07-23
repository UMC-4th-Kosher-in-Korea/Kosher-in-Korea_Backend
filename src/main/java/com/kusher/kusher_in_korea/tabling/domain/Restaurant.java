package com.kusher.kusher_in_korea.tabling.domain;

import com.kusher.kusher_in_korea.tabling.dto.request.CreateRestaurantDto;
import com.kusher.kusher_in_korea.tabling.dto.request.CreateRestaurantMenuDto;
import com.kusher.kusher_in_korea.tabling.dto.request.UpdateRestaurantDto;
import com.kusher.kusher_in_korea.tabling.dto.request.UpdateRestaurantMenuDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public static Restaurant createRestaurant(CreateRestaurantDto createRestaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(createRestaurantDto.getUserId());
        restaurant.setLocation(createRestaurantDto.getLocation());
        restaurant.setRestaurantName(createRestaurantDto.getRestaurantName());
        restaurant.setRestaurantPhone(createRestaurantDto.getRestaurantPhone());
        restaurant.setCapacity(createRestaurantDto.getCapacity());
        restaurant.setOpenTime(LocalDateTime.parse(createRestaurantDto.getOpenTime()));
        restaurant.setCloseTime(LocalDateTime.parse(createRestaurantDto.getCloseTime()));
        restaurant.setDescription(createRestaurantDto.getDescription());
        return restaurant;
    }

    // 비즈니스 로직
    // 식당정보 수정
    public void updateRestaurant(UpdateRestaurantDto updateRestaurantDto) {
        this.setId(updateRestaurantDto.getId());
        this.setOwnerId(updateRestaurantDto.getUserId());
        this.setLocation(updateRestaurantDto.getLocation());
        this.setRestaurantName(updateRestaurantDto.getRestaurantName());
        this.setRestaurantPhone(updateRestaurantDto.getRestaurantPhone());
        this.setCapacity(updateRestaurantDto.getCapacity());
        this.setOpenTime(LocalDateTime.parse(updateRestaurantDto.getOpenTime()));
        this.setCloseTime(LocalDateTime.parse(updateRestaurantDto.getCloseTime()));
        this.setDescription(updateRestaurantDto.getDescription());
    }

    // 식당에 메뉴 추가
    public void addRestaurantMenu(CreateRestaurantMenuDto restaurantMenu) {
        RestaurantMenu newMenu = new RestaurantMenu();
        newMenu.setMenuName(restaurantMenu.getMenuName());
        newMenu.setPrice(restaurantMenu.getPrice());
        newMenu.setMenuDescription(restaurantMenu.getMenuDescription());
        newMenu.setRestaurant(this);
        this.getRestaurantMenus().add(newMenu);
    }

    // 식당에 메뉴 수정
    public void updateRestaurantMenu(Long menuId, UpdateRestaurantMenuDto restaurantMenu) {
        for (RestaurantMenu menu : this.getRestaurantMenus()) {
            if (menu.getId().equals(menuId)) {
                menu.setMenuName(restaurantMenu.getMenuName());
                menu.setPrice(restaurantMenu.getPrice());
                menu.setMenuDescription(restaurantMenu.getDescription());
            }
        }
    }

    // 식당에 메뉴 삭제
    public void deleteRestaurantMenu(Long menuId) {
        for (RestaurantMenu menu : this.getRestaurantMenus()) {
            if (menu.getId().equals(menuId)) {
                this.getRestaurantMenus().remove(menu);
                break;
            }
        }
    }

    //최대수용인원 확인
    public boolean isAvailableVisitorCount(int totalCount, int requestCount) {
        return this.capacity - totalCount >= requestCount;
    }
}
