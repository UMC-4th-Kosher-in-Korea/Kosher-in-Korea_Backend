package com.kusher.kusher_in_korea.ingredient.dto.request;

import com.kusher.kusher_in_korea.ingredient.dto.response.DeliveryDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeDeliveryDto { // 배송지 변경 기능
    private Long orderId;
    private DeliveryDto deliveryDto;
}
