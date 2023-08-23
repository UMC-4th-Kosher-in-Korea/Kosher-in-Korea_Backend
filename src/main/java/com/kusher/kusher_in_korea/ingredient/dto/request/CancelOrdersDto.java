package com.kusher.kusher_in_korea.ingredient.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CancelOrdersDto { // 주문 취소 기능

    private Long orderId;
}
