package com.kusher.kusher_in_korea.tabling.controller;

import com.kusher.kusher_in_korea.tabling.dto.request.CreateReservationDto;
import com.kusher.kusher_in_korea.tabling.dto.request.UpdateReservationDto;
import com.kusher.kusher_in_korea.tabling.dto.response.ReservationDto;
import com.kusher.kusher_in_korea.tabling.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    // 전체 예약 조회
    @GetMapping
    public ResponseEntity<List<ReservationDto>> findAllReservations() {
        List<ReservationDto> allReservations = reservationService.findAllReservation();
        return ResponseEntity.ok(allReservations);
    }

    // 예약 생성
    @PostMapping
    public Long createReservations(@RequestBody CreateReservationDto createReservationDto) {
        Long reservationId = reservationService.createReservation(createReservationDto);
        return reservationId;
    }

    // 예약 수정
    @PutMapping("/{reservationId}")
    public ResponseEntity<Long> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody UpdateReservationDto updateReservationDto
    ) {
        Long updatedReservationId = reservationService.updateReservation(reservationId, updateReservationDto);
        return ResponseEntity.ok(updatedReservationId);
    }

    // 예약 취소
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}
