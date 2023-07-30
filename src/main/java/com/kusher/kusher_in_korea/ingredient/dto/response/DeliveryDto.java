package com.kusher.kusher_in_korea.ingredient.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryDto {
    private String ingredientName;
    private String city;
    private String street;
    private String zipcode;
    private int DeliverStatus;
}
