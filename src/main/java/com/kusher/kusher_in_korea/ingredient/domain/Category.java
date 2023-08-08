package com.kusher.kusher_in_korea.ingredient.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "category")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name; // 카테고리명

    // 카테고리: 식재료는 1대다 관계
    @OneToMany(mappedBy = "category")
    private List<Ingredient> ingredients = new ArrayList<>();

    // 생성 메서드
    public static Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }
}
