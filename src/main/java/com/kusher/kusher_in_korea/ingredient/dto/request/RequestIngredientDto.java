package com.kusher.kusher_in_korea.ingredient.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestIngredientDto { // 식재료 생성/수정 기능
    private String ingredientName;
    private String ingredientCategory;
    private String ingredientImage;
    private String ingredientDescription;
    private int ingredientPrice;
}
