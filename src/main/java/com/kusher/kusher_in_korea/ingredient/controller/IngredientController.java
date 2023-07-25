package com.kusher.kusher_in_korea.ingredient.controller;

import com.kusher.kusher_in_korea.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    // 전체 식재료 조회
    @GetMapping("/api/ingredient")
    public void findAllIngredients() {
        ingredientService.findAllIngredients();
    }

    // 특정 식재료 조회
    @GetMapping("/api/ingredient/{ingredientId}")
    public void findIngredient(@PathVariable("ingredientId") Long ingredientId) {
        ingredientService.findIngredient(ingredientId);
    }

    // 전체 카테고리 조회
    @GetMapping("/api/category")
    public void findAllCategories() {
        ingredientService.findAllCategories();
    }

    // 특정 카테고리에 속한 식재료 조회
    @GetMapping("/api/category/{categoryName}")
    public void findIngredientsByCategory(@PathVariable("categoryName") String categoryName) {
        ingredientService.findIngredientsByCategory(categoryName);
    }

    // 아래부터는 관리용 메서드에 대한 컨트롤러 (추후 구현)
}
