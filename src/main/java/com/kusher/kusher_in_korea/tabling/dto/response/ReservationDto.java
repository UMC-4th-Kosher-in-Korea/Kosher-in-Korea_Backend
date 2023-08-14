package com.kusher.kusher_in_korea.tabling.dto.response;

import com.kusher.kusher_in_korea.tabling.domain.Reservation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class ReservationDto { // 예약 응답
    private Long id; // 예약번호
    private Long userId; // 예약자(유저번호)
    private Long restaurantId; // 식당(식당번호)
    private LocalDate reservationDate; // 예약날짜
    private LocalTime reservationTime; // 예약시간
    private int numberOfPeople; // 예약인원
    private String status; // 예약상태

    public ReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.userId = reservation.getUser().getId();
        this.restaurantId = reservation.getRestaurant().getId();
        this.reservationDate = reservation.getReservationDate();
        this.reservationTime = reservation.getReservationTime();
        this.numberOfPeople = reservation.getNumberOfPeople();
        this.status = reservation.getStatus();
    }
}