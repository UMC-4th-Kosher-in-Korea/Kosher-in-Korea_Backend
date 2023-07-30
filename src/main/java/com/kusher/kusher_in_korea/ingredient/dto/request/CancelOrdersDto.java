package com.kusher.kusher_in_korea.ingredient.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelOrdersDto { // 주문 취소 기능
    private Long orderId;
}
