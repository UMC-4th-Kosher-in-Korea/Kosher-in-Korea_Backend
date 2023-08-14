package com.kusher.kusher_in_korea.ingredient.dto.request;

import com.kusher.kusher_in_korea.ingredient.domain.Address;
import com.kusher.kusher_in_korea.ingredient.domain.OrderStatus;
import com.kusher.kusher_in_korea.ingredient.dto.response.CartIngredientDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateOrdersDto {
    private Long userId;
    private OrderStatus orderStatus;
    private Address address;
    private List<CartIngredientDto> CartIngredients;
    private int totalPrice;
}
