package com.kusher.kusher_in_korea.tabling.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRestaurantDto {

    private Long userId; // 유저번호 (식당 주인임을 확인하기 위함)

    private String location; // 식당주소

    private String restaurantName; // 식당이름

    private String restaurantPhone; // 식당전화번호

    private Long capacity; // 최대수용인원

    private String openTime; // 오픈시간

    private String closeTime; // 마감시간

    private String description; // 식당설명

}
