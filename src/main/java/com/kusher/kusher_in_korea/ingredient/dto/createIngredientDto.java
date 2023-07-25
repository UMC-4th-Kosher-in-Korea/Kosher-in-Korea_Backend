package com.kusher.kusher_in_korea.ingredient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class createIngredientDto {
    private String ingredientName;
    private String ingredientCategory;
    private int ingredientPrice;
}
