package com.kusher.kusher_in_korea.tabling.service;

import com.kusher.kusher_in_korea.tabling.domain.Reservation;
import com.kusher.kusher_in_korea.tabling.dto.request.CreateReservationDto;
import com.kusher.kusher_in_korea.tabling.dto.response.ReservationDto;
import com.kusher.kusher_in_korea.tabling.dto.request.UpdateReservationDto;
import com.kusher.kusher_in_korea.tabling.repository.ReservationRepository;
import com.kusher.kusher_in_korea.tabling.repository.RestaurantRepository;
import com.kusher.kusher_in_korea.tabling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    // 전체 예약 조회
    public List<ReservationDto> findAllReservation() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDtos.add(new ReservationDto(reservation));
        }
        return reservationDtos;
    }

    // 예약 생성
    public Long createReservation(CreateReservationDto createReservationDto) {
        // 여기 isExceed 필요
        Reservation reservation = Reservation.createReservation(createReservationDto);
        reservation.setRestaurant(restaurantRepository.findById(createReservationDto.getRestaurantId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당입니다.")));
        reservation.setUser(userRepository.findById(createReservationDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다.")));
        return reservationRepository.save(reservation).getId();
    }

    // 그 시간대의 최대 수용인원을 초과하는지 검증하는 메서드 필요 (추후 구현)
    private boolean isExceed() {
        return false;
    }

    // 예약 수정(시간, 인원수)
    private Long updateReservation(Long reservationId, UpdateReservationDto updateReservationDto) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));
        // 여기 isExceed 필요
        reservation.changeReservation(updateReservationDto.getReservationDate(), updateReservationDto.getReservationTime(), updateReservationDto.getNumberOfPeople());
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    // 예약 취소(상태 변경)
    private void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));
        reservation.cancelReservation();
        reservationRepository.save(reservation);
    }

}
