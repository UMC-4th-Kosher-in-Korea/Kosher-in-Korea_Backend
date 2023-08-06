package com.kusher.kusher_in_korea.ingredient.dto.response;

import com.kusher.kusher_in_korea.ingredient.domain.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto { // 장바구니 조회 요청에 대한 응답
    private Long cartId;
    private Long userId;
    private List<CartIngredientDto> cartIngredients = new ArrayList<>();
    private int totalPrice;

    public CartDto(Cart cart) {
        this.cartId = cart.getId();
        this.userId = cart.getUser().getId();
        this.cartIngredients = cart.getCartIngredients().stream().map(CartIngredientDto::new).collect(Collectors.toList());
        this.totalPrice = cart.getTotalPrice();
    }
}
