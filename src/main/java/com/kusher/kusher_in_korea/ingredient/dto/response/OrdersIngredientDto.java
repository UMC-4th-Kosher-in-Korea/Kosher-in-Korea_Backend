package com.kusher.kusher_in_korea.ingredient.dto.response;

import com.kusher.kusher_in_korea.ingredient.domain.OrdersIngredient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersIngredientDto {
    private Long orderIngredientId;
    private String ingredientName;
    private String ingredientImage;
    private int price;
    private int quantity;

    public OrdersIngredientDto(OrdersIngredient ordersIngredient) {
        this.orderIngredientId = ordersIngredient.getId();
        this.ingredientName = ordersIngredient.getIngredient().getName();
        this.ingredientImage = ordersIngredient.getIngredient().getImage();
        this.price = ordersIngredient.getTotalPrice();
        this.quantity = ordersIngredient.getCount();
    }
}
