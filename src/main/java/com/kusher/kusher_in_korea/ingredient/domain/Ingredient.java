package com.kusher.kusher_in_korea.ingredient.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Ingredient { // 식재료 테이블
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    private String name; // 식재료명

    private int price; // 식재료가격

    @ManyToOne(fetch = FetchType.LAZY) // 식재료는 카테고리와 다대일 관계
    @JoinColumn(name = "category_id")
    private Category category; // 이 식재료의 카테고리

    // 생성 메서드
    public static Ingredient createIngredient(String name, int price, Category category) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setPrice(price);
        ingredient.setCategory(category);
        return ingredient;
    }

    // 비즈니스 로직
    // 식재료 가격 변경
    public void updateIngredient(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
