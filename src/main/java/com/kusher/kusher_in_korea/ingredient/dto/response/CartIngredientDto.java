package com.kusher.kusher_in_korea.ingredient.dto.response;

import com.kusher.kusher_in_korea.ingredient.domain.CartIngredient;
import lombok.Getter;

@Getter
public class CartIngredientDto {

    private Long cartIngredientId;
    private String ingredientName;
    private String ingredientImage;
    private int price;
    private int quantity;

    public CartIngredientDto(CartIngredient cartIngredient) {
        this.cartIngredientId = cartIngredient.getId();
        this.ingredientName = cartIngredient.getIngredient().getName();
        this.ingredientImage = cartIngredient.getIngredient().getImage();
        this.price = cartIngredient.getIngredient().getPrice();
        this.quantity = cartIngredient.getCount();
    }
}
