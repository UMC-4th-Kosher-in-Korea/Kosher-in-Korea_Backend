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

    // 생성 메서드
    public static Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        return cart;
    }

    // 비즈니스 로직
    // 장바구니에 재료 추가
    public void addCartIngredient(CartIngredient cartIngredient) {
        cartIngredient.setCart(this);
        cartIngredients.add(cartIngredient);
    }

    // 장바구니에 담긴 재료 삭제
    public void removeCartIngredient(CartIngredient cartIngredient) {
        cartIngredients.remove(cartIngredient);
    }

    // 장바구니 특정 재료 개수 증가
    public void addCartIngredient(CartIngredient cartIngredient, int quantity) {
        cartIngredient.addCount();
    }

    // 장바구니 특정 재료 개수 감소
    public void removeCartIngredient(CartIngredient cartIngredient, int quantity) {
        cartIngredient.subtractCount();
    }

    // 장바구니 비우기 (결제 완료 시 마지막에 사용할 목적)
    public void clearCart() {
        cartIngredients.clear();
    }
}
