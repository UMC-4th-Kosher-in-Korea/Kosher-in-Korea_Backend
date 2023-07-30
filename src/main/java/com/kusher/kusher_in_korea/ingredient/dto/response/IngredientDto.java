package com.kusher.kusher_in_korea.ingredient.dto.response;

import com.kusher.kusher_in_korea.ingredient.domain.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientDto {
    private Long ingredientId;
    private String ingredientName;
    private int price;
    private String categoryName;

    public IngredientDto(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.ingredientName = ingredient.getName();
        this.price = ingredient.getPrice();
        this.categoryName = ingredient.getCategory().getName();
    }
}
