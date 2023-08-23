package com.kusher.kusher_in_korea.ingredient.dto.request;

import com.kusher.kusher_in_korea.ingredient.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeAddressDto { // 배송지 변경 기능

    private Long deliveryId;
    private Long userId;
    private Address address;
}
