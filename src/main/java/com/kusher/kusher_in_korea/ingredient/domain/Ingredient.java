package com.kusher.kusher_in_korea.ingredient.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ingredient")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient { // 식재료 테이블
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    private String name; // 식재료명

    private String image; // 식재료 이미지

    private int price; // 식재료가격

    @ManyToOne(fetch = FetchType.LAZY) // 식재료는 카테고리와 다대일 관계
    @JoinColumn(name = "category_id")
    private Category category; // 이 식재료의 카테고리

    // 생성 메서드
    public static Ingredient createIngredient(String name, String image, int price, Category category) {
        Ingredient ingredient = new Ingredient();
        ingredient.name = name;
        ingredient.image = image;
        ingredient.price = price;
        ingredient.category = category;
        return ingredient;
    }

    // 비즈니스 로직
    // 식재료 정보 수정
    public void updateIngredient(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
