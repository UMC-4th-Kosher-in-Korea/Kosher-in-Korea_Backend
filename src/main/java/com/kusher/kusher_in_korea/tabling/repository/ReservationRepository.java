package com.kusher.kusher_in_korea.tabling.repository;

import com.kusher.kusher_in_korea.tabling.domain.Reservation;
import com.kusher.kusher_in_korea.tabling.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

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
