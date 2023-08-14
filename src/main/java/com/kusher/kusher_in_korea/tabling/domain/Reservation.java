package com.kusher.kusher_in_korea.tabling.domain;

import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.tabling.dto.request.CreateReservationDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Table(name = "reservation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private int numberOfPeople; // 예약인원

    private String status; // 예약상태

    // 생성 메서드
    public static Reservation createReservation(CreateReservationDto createReservationDto) {
        Reservation reservation = new Reservation();
        reservation.reservationDate = createReservationDto.getReservationDate();
        reservation.reservationTime = createReservationDto.getReservationTime();
        reservation.numberOfPeople = createReservationDto.getNumberOfPeople();
        reservation.status = "예약완료";
        return reservation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // 비즈니스 로직
    // 예약 변경(날짜, 시간, 인원수)
    public void changeReservation(LocalDate reservationDate, LocalTime reservationTime, int numberOfPeople) {
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.numberOfPeople = numberOfPeople;
    }

    // 예약 취소(예시)
    public void cancelReservation() {
        this.status = "예약취소";
    }

    public boolean isCancelable() {
        return this.status.equals("예약완료");
    }
}
