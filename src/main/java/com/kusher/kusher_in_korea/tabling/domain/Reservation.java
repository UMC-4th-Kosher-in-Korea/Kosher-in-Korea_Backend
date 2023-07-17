package com.kusher.kusher_in_korea.tabling.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reservation")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id; // 예약번호

    @ManyToOne(fetch = FetchType.LAZY) // 유저 - 예약은 1대다
    @JoinColumn(name = "user_id")
    private User user; // 예약자(유저번호)

    @ManyToOne(fetch = FetchType.LAZY) // 식당 - 예약은 1대다
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant; // 식당(식당번호)

    private LocalDateTime reservationDateTime; // 예약날짜시간

    private Long numberOfPeople; // 예약인원

    private String status; // 예약상태

    // 생성 메서드
    public static Reservation createReservation(User user, Restaurant restaurant, LocalDateTime reservationDateTime, Long numberOfPeople, String status) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRestaurant(restaurant);
        reservation.setReservationDateTime(reservationDateTime);
        reservation.setNumberOfPeople(numberOfPeople);
        reservation.setStatus(status);
        return reservation;
    }

    // 비즈니스 로직
    // 예약 수정(변경)
    public void changeReservation(Restaurant restaurant, LocalDateTime reservationDateTime, Long numberOfPeople) {
        this.reservationDateTime = reservationDateTime;
        this.numberOfPeople = numberOfPeople;
    }

    // 예약 취소(예시)
    public void cancelReservation() {
        this.status = "취소";
    }

}
