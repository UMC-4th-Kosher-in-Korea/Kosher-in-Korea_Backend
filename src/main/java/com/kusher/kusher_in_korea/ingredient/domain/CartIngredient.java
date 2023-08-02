package com.kusher.kusher_in_korea.ingredient.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "cart_ingredient")
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

    public int getTotalPrice() {
        return ingredient.getPrice() * getCount();
    }
}
