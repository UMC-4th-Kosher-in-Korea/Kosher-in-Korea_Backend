package com.kusher.kusher_in_korea.ingredient.dto.response;

import com.kusher.kusher_in_korea.ingredient.domain.Delivery;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrdersDto { // 주문 조회에 대한 응답
    private Long orderId;
    private String orderStatus;
    private DeliveryDto delivery;
    private LocalDateTime orderDateTime;
}
