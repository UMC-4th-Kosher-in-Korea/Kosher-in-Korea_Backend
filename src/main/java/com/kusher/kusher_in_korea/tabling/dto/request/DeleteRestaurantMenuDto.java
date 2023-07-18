package com.kusher.kusher_in_korea.tabling.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteRestaurantMenuDto { // 식당 메뉴 삭제 요청
    private Long menuId; // 메뉴번호
    private Long ownerId; // 식당 주인임을 확인하기 위함
}
