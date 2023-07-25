package com.kusher.kusher_in_korea.ingredient.repository;

import com.kusher.kusher_in_korea.ingredient.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    // 특정 user의 주문 조회
    List<Orders> findByUserId(Long userId);
}
