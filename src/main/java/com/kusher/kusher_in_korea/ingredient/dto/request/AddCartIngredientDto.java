package com.kusher.kusher_in_korea.ingredient.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCartIngredientDto {
    private Long cartId;
    private Long ingredientId;
}
