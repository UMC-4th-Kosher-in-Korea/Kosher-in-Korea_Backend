package com.kusher.kusher_in_korea.ingredient.controller;

import com.kusher.kusher_in_korea.ingredient.dto.request.RequestCategoryDto;
import com.kusher.kusher_in_korea.ingredient.dto.request.RequestIngredientDto;
import com.kusher.kusher_in_korea.ingredient.dto.response.CategoryDto;
import com.kusher.kusher_in_korea.ingredient.dto.response.IngredientDto;
import com.kusher.kusher_in_korea.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    // 전체 식재료 조회
    @GetMapping("/api/ingredient")
    public ResponseEntity<List<IngredientDto>> findAllIngredients() {
        List<IngredientDto> ingredients = ingredientService.findAllIngredients();
        return ResponseEntity.ok(ingredients);
    }

    // 특정 식재료 조회
    @GetMapping("/api/ingredient/{ingredientId}")
    public ResponseEntity<IngredientDto> findIngredient(@PathVariable("ingredientId") Long ingredientId) {
        IngredientDto ingredient = ingredientService.findIngredient(ingredientId);
        return ResponseEntity.ok(ingredient);
    }

    // 전체 카테고리 조회
    @GetMapping("/api/category")
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        List<CategoryDto> categories = ingredientService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    // 특정 카테고리에 속한 식재료 조회
    @GetMapping("/api/category/{categoryName}/ingredient")
    public ResponseEntity<List<IngredientDto>> findIngredientsByCategory(@PathVariable("categoryName") String categoryName) {
        List<IngredientDto> ingredients = ingredientService.findIngredientsByCategory(categoryName);
        return ResponseEntity.ok(ingredients);
    }

    // 아래부터는 서버에서 사용하는 관리용 메서드에 대한 Controller

    // 식재료 추가
    @PostMapping("/api/ingredient")
    public ResponseEntity<Long> addIngredient(RequestIngredientDto requestIngredientDto) {
        Long id = ingredientService.addIngredient(requestIngredientDto);
        return ResponseEntity.ok(id);
    }

    // 식재료 수정
    @PutMapping("/api/ingredient/{ingredientId}")
    public ResponseEntity<Long> updateIngredient(@PathVariable("ingredientId") Long ingredientId, RequestIngredientDto requestIngredientDto) {
        Long id = ingredientService.updateIngredient(ingredientId, requestIngredientDto);
        return ResponseEntity.ok(id);
    }

    // 식재료 삭제
    @DeleteMapping("/api/ingredient/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
       ingredientService.deleteIngredient(ingredientId);
         return ResponseEntity.ok().build();
    }

    // 카테고리 추가
    @PostMapping("/api/category")
    public ResponseEntity<Long> addCategory(@RequestBody RequestCategoryDto requestCategoryDto) {
        Long id = ingredientService.addCategory(requestCategoryDto.getCategoryName());
        return ResponseEntity.ok(id);
    }

    // 카테고리 수정
    @PutMapping("/api/category/{categoryId}")
    public ResponseEntity<Long> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody RequestCategoryDto requestCategoryDto) {
        Long id = ingredientService.updateCategory(categoryId, requestCategoryDto.getCategoryName());
        return ResponseEntity.ok(id);
    }

    // 카테고리 삭제
    @DeleteMapping("/api/category/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        ingredientService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }

}
