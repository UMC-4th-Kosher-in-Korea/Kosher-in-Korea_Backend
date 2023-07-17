package com.kusher.kusher_in_korea.tabling.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateReservationDto {
    private Long reservationId; // 예약번호
    private String reservationDate; // 예약날짜
    private String reservationTime; // 예약시간
    private Long numberOfPeople; // 예약인원
    private String status; // 예약상태
}
