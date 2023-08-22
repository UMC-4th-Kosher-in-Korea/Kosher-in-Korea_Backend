package com.kusher.kusher_in_korea.ingredient.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "cart_ingredient")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartIngredient { // 장바구니와 식재료의 다대다 관계로 인한 중간 테이블

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_ingredient_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient; // 장바구니에 담은 식재료

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart; // 장바구니

    private int count; // 장바구니에 담은 식재료 수량

    // 생성 메서드
    public static CartIngredient createCartIngredient(Cart cart, int count, Ingredient ingredient) {
        return new CartIngredient(ingredient, cart, count);
    }

    protected CartIngredient(Ingredient ingredient, Cart cart, int count) {
        this.ingredient = ingredient;
        this.cart = cart;
        this.count = count;
    }

    public int getTotalPrice() {
        return ingredient.getPrice() * getCount();
    }

    public void addCount() { // + 버튼 클릭 시
        ++count;
    }

    public void subtractCount() { // - 버튼 클릭 시
        --count;
    }
}
