package com.kusher.kusher_in_korea.ingredient.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderIngredient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_ingredient_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient; // 주문한 식재료

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders; // 주문

    private int count; // 주문 수량

    private int price; // 주문 가격
}
