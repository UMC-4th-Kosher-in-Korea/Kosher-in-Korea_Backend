package com.kusher.kusher_in_korea.tabling.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    private String reservationDate; // 예약날짜

    private String reservationTime; // 예약시간

    private Long numberOfPeople; // 예약인원

    private String status; // 예약상태

    // 생성 메서드
    public static Reservation createReservation(User user, Restaurant restaurant, String reservationDate, String reservationTime, Long numberOfPeople, String status) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRestaurant(restaurant);
        reservation.setReservationDate(reservationDate);
        reservation.setReservationTime(reservationTime);
        reservation.setNumberOfPeople(numberOfPeople);
        reservation.setStatus(status);
        return reservation;
    }

    // 비즈니스 로직
    // 예약 정보 수정
    public void updateReservation(User user, Restaurant restaurant, String reservationDate, String reservationTime, Long numberOfPeople, String status) {
        this.user = user;
        this.restaurant = restaurant;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
    }

    // 예약 취소(예시)
    public void cancelReservation() {
        this.status = "취소";
    }

    // 예약 변경
    public void changeReservation(String reservationDate, String reservationTime, Long numberOfPeople) {
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.numberOfPeople = numberOfPeople;
    }

}
