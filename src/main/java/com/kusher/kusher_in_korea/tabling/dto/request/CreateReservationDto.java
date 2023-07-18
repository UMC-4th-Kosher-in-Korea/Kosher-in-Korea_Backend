package com.kusher.kusher_in_korea.tabling.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateReservationDto { // 예약 추가 요청
    private Long userId; // 예약자(유저번호)
    private Long restaurantId; // 식당(식당번호)
    private String reservationDate; // 예약날짜
    private String reservationTime; // 예약시간
    private Long numberOfPeople; // 예약인원
    private String status; // 예약상태
}
