package com.kusher.kusher_in_korea.ingredient.dto.response;

import com.kusher.kusher_in_korea.ingredient.domain.Delivery;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryDto {
    private Long deliveryId;
    private String city;
    private String street;
    private String zipcode;
    private int DeliverStatus;

    public DeliveryDto(Delivery delivery) {
        this.deliveryId = delivery.getId();
        this.city = delivery.getAddress().getCity();
        this.street = delivery.getAddress().getStreet();
        this.zipcode = delivery.getAddress().getZipcode();
        this.DeliverStatus = delivery.getStatus().ordinal();
    }
}
