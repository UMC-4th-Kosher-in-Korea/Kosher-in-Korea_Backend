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
}
