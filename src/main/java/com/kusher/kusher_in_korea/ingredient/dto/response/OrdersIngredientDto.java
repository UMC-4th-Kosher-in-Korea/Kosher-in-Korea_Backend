package com.kusher.kusher_in_korea.ingredient.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersIngredientDto {
    private String ingredientName;
    private String categoryName;
    private int price;
    private int quantity;
}
