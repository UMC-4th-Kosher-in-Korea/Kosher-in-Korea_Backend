package com.kusher.kusher_in_korea.ingredient.service;

import com.kusher.kusher_in_korea.ingredient.domain.Orders;
import com.kusher.kusher_in_korea.ingredient.dto.response.OrdersDto;
import com.kusher.kusher_in_korea.ingredient.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    // 주문 생성
    public Long createOrder(Orders orders) {
        Orders savedOrder = ordersRepository.save(orders);
        return savedOrder.getId();
    }

    // 주문 취소
    public void cancelOrder(Long orderId) {
        Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));
        order.cancel();
    }

    // 주문 수정(배송지 수정)
    public Long updateOrder(Long orderId, Orders orders) {
        Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));
        order.update(orders);
        return order.getId();
    }

    // user의 주문 조회
    public List<OrdersDto> getUserOrders(Long userId) {
        List<Orders> orders = ordersRepository.findByUserId(userId);
        return orders.stream().map(OrdersDto::new).collect(Collectors.toList());
    }

    // 특정 주문 조회
    public OrdersDto getOrder(Long orderId) {
        Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));
        return new OrdersDto(order);
    }

    // 전체 주문 조회: 관리자용 메서드
    public List<OrdersDto> getOrders() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream().map(OrdersDto::new).collect(Collectors.toList());
    }

}
