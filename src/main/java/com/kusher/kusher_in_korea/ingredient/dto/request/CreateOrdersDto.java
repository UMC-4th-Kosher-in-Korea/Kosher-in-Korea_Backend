package com.kusher.kusher_in_korea.ingredient.dto.request;

import com.kusher.kusher_in_korea.ingredient.domain.Delivery;
import com.kusher.kusher_in_korea.ingredient.domain.OrderStatus;
import com.kusher.kusher_in_korea.ingredient.dto.response.OrdersIngredientDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateOrdersDto {
    private Long userId;
    private OrderStatus orderStatus;
    private Delivery delivery;
    private List<OrdersIngredientDto> orderIngredients;
    private int totalPrice;
}
