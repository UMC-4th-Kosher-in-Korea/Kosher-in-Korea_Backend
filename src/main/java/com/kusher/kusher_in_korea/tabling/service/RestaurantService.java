package com.kusher.kusher_in_korea.tabling.service;

import com.kusher.kusher_in_korea.tabling.domain.Reservation;
import com.kusher.kusher_in_korea.tabling.domain.Restaurant;
import com.kusher.kusher_in_korea.tabling.domain.RestaurantMenu;
import com.kusher.kusher_in_korea.tabling.domain.User;
import com.kusher.kusher_in_korea.tabling.dto.request.*;
import com.kusher.kusher_in_korea.tabling.dto.response.ReservationDto;
import com.kusher.kusher_in_korea.tabling.dto.response.RestaurantDto;
import com.kusher.kusher_in_korea.tabling.dto.response.RestaurantMenuDto;
import com.kusher.kusher_in_korea.tabling.repository.ReservationRepository;
import com.kusher.kusher_in_korea.tabling.repository.RestaurantRepository;
import com.kusher.kusher_in_korea.tabling.repository.UserRepository;
import com.kusher.kusher_in_korea.util.exception.CustomException;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    // 전체 커셔 식당 조회
    public List<RestaurantDto> findAllRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDtos.add(new RestaurantDto(restaurant));
        }
        return restaurantDtos;
    }

    // 특정 식당의 정보 조회
    public Optional<RestaurantDto> findRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new CustomException(ResponseCode.RESTAURANT_NOT_FOUND));
        return Optional.of(new RestaurantDto(restaurant));
    }

    // 특정 식당의 메뉴 조회
    public List<RestaurantMenuDto> findRestaurantMenu(Long restaurantId) {
        List<RestaurantMenu> menus = restaurantRepository.getMenus(PageRequest.of(0, 10), restaurantId);
        List<RestaurantMenuDto> menuDtos = new ArrayList<>();
        for (RestaurantMenu menu : menus) {
            menuDtos.add(new RestaurantMenuDto(menu));
        }
        return menuDtos;
    }

    // 이 아래부터는 커셔 식당 주인만 가능한 기능들이다. 주인의 클라이언트는 일단 미구현 상태이기에, 아래 메서드들은 일단 서버에서 자체적으로 활용한다.

    // 요청한 유저가 식당 주인인지 확인
    public boolean isOwner(Long restaurantId, Long ownerId) {
        Long id = restaurantRepository.findById(restaurantId).orElseThrow(() -> new CustomException(ResponseCode.RESTAURANT_NOT_FOUND)).getOwnerId();
        return (id.equals(ownerId));
    }

    // 특정 식당의 예약 내역 조회 (오래된 예약이 뒤에 나오도록 정렬)
    private List<ReservationDto> getReservationList(Long restaurantId, RequestReservationListDto requestReservationListDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new CustomException(ResponseCode.RESTAURANT_NOT_FOUND));
        if (!isOwner(restaurant.getOwnerId(), requestReservationListDto.getUserId())) {
            throw new CustomException(ResponseCode.NOT_RESTAURANT_OWNER);
        }
        List<Reservation> reservationList = reservationRepository.findByRestaurantIdOrderByReservationDateDescReservationTimeDesc(restaurantId);
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            reservationDtoList.add(new ReservationDto(reservation));
        }
        return reservationDtoList;
    }

    // 새 식당 추가 (점주 타입만 가능)
    public Long saveRestaurant(CreateRestaurantDto createRestaurantDto) {
        User user = userRepository.findById(createRestaurantDto.getUserId()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        if (!Objects.equals(user.getUserType(), "점주"))
            throw new CustomException(ResponseCode.NOT_RESTAURANT_OWNER);
        Restaurant restaurant = Restaurant.createRestaurant(createRestaurantDto);
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

    // 특정 식당 정보 수정
    public Long updateRestaurant(Long restaurantId, UpdateRestaurantDto restaurantDto) {
        Long userId = restaurantDto.getUserId();
        if (!isOwner(restaurantId, userId)) throw new CustomException(ResponseCode.NOT_RESTAURANT_OWNER);

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new CustomException(ResponseCode.RESTAURANT_NOT_FOUND));
        restaurant.updateRestaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

    // 특정 식당 메뉴 추가
    public Long saveRestaurantMenu(Long restaurantId, CreateRestaurantMenuDto createRestaurantMenuDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new CustomException(ResponseCode.RESTAURANT_NOT_FOUND));
        if (!Objects.equals(createRestaurantMenuDto.getOwnerId(), restaurant.getOwnerId()))
            throw new CustomException(ResponseCode.NOT_RESTAURANT_OWNER);

        restaurant.addRestaurantMenu(createRestaurantMenuDto);
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

    // 특정 식당 메뉴 수정
    public Long updateRestaurantMenu(Long restaurantId, Long menuId, UpdateRestaurantMenuDto restaurantMenuDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new CustomException(ResponseCode.RESTAURANT_NOT_FOUND));
        if (!Objects.equals(restaurantMenuDto.getOwnerId(), restaurant.getOwnerId()))
            throw new CustomException(ResponseCode.NOT_RESTAURANT_OWNER);
        restaurant.updateRestaurantMenu(menuId, restaurantMenuDto);
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

    // 특정 식당 메뉴 삭제
    public Long deleteRestaurantMenu(Long restaurantId, Long menuId, DeleteRestaurantMenuDto deleteRestaurantMenuDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new CustomException(ResponseCode.RESTAURANT_NOT_FOUND));
        if (!Objects.equals(deleteRestaurantMenuDto.getOwnerId(), restaurant.getOwnerId()))
            throw new CustomException(ResponseCode.NOT_RESTAURANT_OWNER);
        restaurant.deleteRestaurantMenu(menuId);
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

}
