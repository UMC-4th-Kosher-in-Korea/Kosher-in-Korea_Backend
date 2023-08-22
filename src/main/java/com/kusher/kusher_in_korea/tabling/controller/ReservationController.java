package com.kusher.kusher_in_korea.tabling.controller;

import com.kusher.kusher_in_korea.tabling.dto.request.CreateReservationDto;
import com.kusher.kusher_in_korea.tabling.dto.request.UpdateReservationDto;
import com.kusher.kusher_in_korea.tabling.dto.response.ReservationDto;
import com.kusher.kusher_in_korea.tabling.service.ReservationService;
import com.kusher.kusher_in_korea.util.api.ApiResponse;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    // 전체 예약 조회 (관리자용)
    @GetMapping
    public ApiResponse<List<ReservationDto>> findAllReservations() {
        List<ReservationDto> allReservations = reservationService.findAllReservation();
        return ApiResponse.success(allReservations, ResponseCode.RESERVATION_READ_SUCCESS.getMessage());
    }

    // 예약 생성
    @PostMapping
    public ApiResponse<Long> createReservations(@RequestBody CreateReservationDto createReservationDto) {
        Long id = reservationService.createReservation(createReservationDto);
        return ApiResponse.success(id, ResponseCode.RESERVATION_CREATE_SUCCESS.getMessage());
    }

    // 예약 수정
    @PutMapping("/{reservationId}")
    public ApiResponse<Long> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody UpdateReservationDto updateReservationDto
    ) {
        Long updatedReservationId = reservationService.updateReservation(reservationId, updateReservationDto);
        return ApiResponse.success(updatedReservationId, ResponseCode.RESERVATION_UPDATE_SUCCESS.getMessage());
    }

    // 예약 취소
    @PutMapping("/{reservationId}/cancel")
    public ApiResponse<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ApiResponse.success(null, ResponseCode.RESERVATION_CANCEL_SUCCESS.getMessage());
    }
}
