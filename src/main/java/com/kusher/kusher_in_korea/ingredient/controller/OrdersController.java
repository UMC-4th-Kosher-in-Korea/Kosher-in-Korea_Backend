package com.kusher.kusher_in_korea.ingredient.controller;

import com.kusher.kusher_in_korea.ingredient.domain.Orders;
import com.kusher.kusher_in_korea.ingredient.dto.response.OrdersDto;
import com.kusher.kusher_in_korea.ingredient.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    // 주문 생성
    @PostMapping("/api/orders")
    public ResponseEntity<Long> createOrder(Orders orders) {
        Long orderId = ordersService.createOrder(orders);
        return ResponseEntity.ok(orderId);
    }

    // 주문 수정(배송지 변경)
    @PutMapping("/api/orders/{orderId}")
    public ResponseEntity<Long> updateOrder(@PathVariable Long orderId, Orders orders) {
        Long updatedOrderId = ordersService.updateOrder(orderId, orders);
        return ResponseEntity.ok(updatedOrderId);
    }

    // 주문 취소
    @PostMapping("/api/orders/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        ordersService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }

    // 특정 주문 조회
    @GetMapping("/api/orders/{orderId}")
    public ResponseEntity<OrdersDto> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(ordersService.getOrder(orderId));
    }

    // 전체 주문 조회: 관리자용 메서드
    @GetMapping("/api/orders")
    public ResponseEntity<List<OrdersDto>> getOrders() {
        return ResponseEntity.ok(ordersService.getOrders());
    }

}
