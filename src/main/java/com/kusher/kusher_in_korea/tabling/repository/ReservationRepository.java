package com.kusher.kusher_in_korea.tabling.repository;

import com.kusher.kusher_in_korea.tabling.domain.Reservation;
import com.kusher.kusher_in_korea.tabling.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 특정 유저의 예약 조회
    List<Reservation> findAllByUserId(Long userId);

    // 특정 식당의 예약 조회 (오래된 시간이 늦게 나오도록 정렬)
    List<Reservation> findByRestaurantIdOrderByReservationDateDescReservationTimeDesc(Long restaurantId);

    //동일한 날짜,시간대의 식당 총 예약인원 확인
    @Query("select sum(r.numberOfPeople) "
            + " from Reservation r "
            + " where r.restaurant = :restaurant "
            + " AND r.reservationDate = :reservationDate"
            + " AND r.reservationTime = :reservationTime")
    Optional<Integer> countTotalVisitorCount(
            @Param("restaurant") Restaurant restaurant,
            @Param("reservationDate") LocalDate reservationDate,
            @Param("reservationTime") LocalTime reservationTime);
}
