package com.kusher.kusher_in_korea.ingredient.domain;

import com.kusher.kusher_in_korea.auth.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "cart")
public class Cart { // User와 장바구니는 일대일 관계
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(mappedBy = "cart",fetch = FetchType.LAZY)
    private User user; // 장바구니를 소유한 회원

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartIngredient> cartIngredients = new ArrayList<>(); // 장바구니에 담긴 재료들

    private int totalPrice = 0; // 장바구니에 담긴 재료들의 총 가격

    // 생성 메서드
    public static Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        return cart;
    }

    // 장바구니에 상품 추가
    public void updateTotalPrice() {
        totalPrice = 0;
        for (CartIngredient cartIngredient : cartIngredients) {
            totalPrice += cartIngredient.getTotalPrice();
        }
    }

    // 장바구니에 재료 추가
    public void addCartIngredientPrice(CartIngredient cartIngredient) {
        totalPrice += cartIngredient.getTotalPrice();
    }

    // 장바구니에 재료 삭제
    public void removeCartIngredientPrice(CartIngredient cartIngredient) {
        totalPrice -= cartIngredient.getTotalPrice();
    }

    // 장바구니 비우기 (결제 완료 시 마지막에 사용할 목적)
    public void clearCart() {
        cartIngredients.clear();
        totalPrice = 0;
    }
}
