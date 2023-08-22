package com.kusher.kusher_in_korea.ingredient.dto.response;

import com.kusher.kusher_in_korea.ingredient.domain.Ingredient;
import lombok.Getter;

@Getter
public class IngredientDto {

    private Long ingredientId;
    private String ingredientName;
    private String ingredientImage;
    private int price;
    private String categoryName;

    public IngredientDto(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.ingredientName = ingredient.getName();
        this.ingredientImage = ingredient.getImage();
        this.price = ingredient.getPrice();
        this.categoryName = ingredient.getCategory().getName();
    }
}
