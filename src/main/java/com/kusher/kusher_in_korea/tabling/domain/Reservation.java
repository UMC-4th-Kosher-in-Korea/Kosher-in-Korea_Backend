package com.kusher.kusher_in_korea.tabling.domain;

import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.tabling.dto.request.CreateReservationDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id; // 예약번호

    @ManyToOne(fetch = FetchType.LAZY) // 유저 - 예약은 1대다
    @JoinColumn(name = "user_id")
    private User user; // 예약자(유저번호)

    @ManyToOne(fetch = FetchType.LAZY) // 식당 - 예약은 1대다
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant; // 식당(식당번호)

    private LocalDate reservationDate; // 예약날짜

    private LocalTime reservationTime; // 예약시간

    private Long numberOfPeople; // 예약인원

    private String status; // 예약상태

    // 생성 메서드
    public static Reservation createReservation(CreateReservationDto createReservationDto) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(LocalDate.parse(createReservationDto.getReservationDate()));
        reservation.setReservationTime(LocalTime.parse(createReservationDto.getReservationTime()));
        reservation.setNumberOfPeople(createReservationDto.getNumberOfPeople());
        reservation.setStatus("예약완료");
        return reservation;
    }

    // 비즈니스 로직
    // 예약 변경(날짜, 시간, 인원수)
    public void changeReservation(String reservationDate, String reservationTime, Long numberOfPeople) {
        this.reservationDate = LocalDate.parse(reservationDate);
        this.reservationTime = LocalTime.parse(reservationTime);
        this.numberOfPeople = numberOfPeople;
    }

    // 예약 취소(예시)
    public void cancelReservation() {
        this.status = "예약취소";
    }

}
