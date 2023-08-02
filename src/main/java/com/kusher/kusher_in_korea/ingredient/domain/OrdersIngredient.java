package com.kusher.kusher_in_korea.ingredient.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrdersIngredient {
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

    // 생성 메서드
    public static OrdersIngredient createOrderIngredient(Ingredient ingredient, int count) {
        OrdersIngredient ordersIngredient = new OrdersIngredient();
        ordersIngredient.setIngredient(ingredient);
        ordersIngredient.setCount(count);
        return ordersIngredient;
    }

    // 이 주문상품의 가격 조회
    public int getTotalPrice() {
        return getIngredient().getPrice() * getCount();
    }
}
