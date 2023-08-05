package com.kusher.kusher_in_korea.ingredient.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCartIngredientDto { // 장바구니에 재료 추가 요청
    private Long cartId;
    private Long ingredientId;
    private int count;
}
