package com.kusher.kusher_in_korea.tabling.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRestaurantMenuDto { // 식당 메뉴 수정 요청

    private Long menuId; // 메뉴번호
    private Long ownerId; // 유저번호 (식당 주인임을 확인하기 위함)
    private Long restaurantId; // 식당번호
    private String menuName; // 메뉴이름
    private int Price; // 메뉴가격
    private String Description; // 메뉴설명
}
