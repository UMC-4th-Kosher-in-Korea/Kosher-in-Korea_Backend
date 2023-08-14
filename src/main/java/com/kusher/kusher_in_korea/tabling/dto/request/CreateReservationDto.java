package com.kusher.kusher_in_korea.tabling.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class CreateReservationDto { // 예약 추가 요청
    private Long userId; // 예약자(유저번호)
    private Long restaurantId; // 식당(식당번호)
    private LocalDate reservationDate; // 예약날짜
    private LocalTime reservationTime; // 예약시간
    private int numberOfPeople; // 예약인원
    private String status; // 예약상태
}
