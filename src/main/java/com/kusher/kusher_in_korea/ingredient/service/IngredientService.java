package com.kusher.kusher_in_korea.ingredient.service;

import com.kusher.kusher_in_korea.ingredient.domain.Category;
import com.kusher.kusher_in_korea.ingredient.domain.Ingredient;
import com.kusher.kusher_in_korea.ingredient.dto.createIngredientDto;
import com.kusher.kusher_in_korea.ingredient.repository.CategoryRepository;
import com.kusher.kusher_in_korea.ingredient.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService { // 식재료 및 카테고리를 제어한다.

    private final IngredientRepository ingredientRepository;
    private final CategoryRepository categoryRepository;

    // 전체 식재료 조회
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    // 클라이언트가 클릭했을 때를 대비한 특정 식재료 조회
    public Ingredient findIngredient(Long ingredientId) {
        return ingredientRepository.findById(ingredientId).orElseThrow(() -> new IllegalStateException("존재하지 않는 식재료입니다."));
    }

    // 전체 카테고리 조회
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    // 특정 카테고리에 속한 식재료 조회
    public List<Ingredient> findIngredientsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(() -> new IllegalStateException("존재하지 않는 카테고리입니다."));
        return ingredientRepository.findByCategoryId(category.getId());
    }

    /**
     * 아래부터는 서버에서 사용하기 위한 관리용 메서드
     */
    // 식재료 추가
    public Long addIngredient(createIngredientDto createIngredientDto) {
        validateDuplicateIngredient(createIngredientDto.getIngredientName());
        Category category = categoryRepository.findByName(createIngredientDto.getIngredientCategory()).orElseThrow(() -> new IllegalStateException("존재하지 않는 카테고리입니다."));
        Ingredient ingredient = Ingredient.createIngredient(createIngredientDto.getIngredientName(), createIngredientDto.getIngredientPrice(), category);
        ingredientRepository.save(ingredient);
        return ingredient.getId();
    }

    // 중복 식재료명 감지
    private void validateDuplicateIngredient(String name) {
        ingredientRepository.findByName(name).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 식재료입니다.");
        });
    }

    // 식재료 수정
    public Long updateIngredient(Long ingredientId, String ingredientName, Long categoryId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(() -> new IllegalStateException("존재하지 않는 식재료입니다."));
        ingredient.setName(ingredientName);
        ingredient.setCategory(categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException("존재하지 않는 카테고리입니다.")));
        return ingredient.getId();
    }

    // 식재료 삭제
    private void deleteIngredient(Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

    // 카테고리 추가
    public Long addCategory(Category category) {
        validateDuplicateCategory(category.getName());
        categoryRepository.save(category);
        return category.getId();
    }

    // 중복 카테고리 감지
    private void validateDuplicateCategory(String name) {
        categoryRepository.findByName(name).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 카테고리입니다.");
        });
    }

    // 카테고리 수정
    public Long updateCategory(Long categoryId, String categoryName) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException("존재하지 않는 카테고리입니다."));
        category.setName(categoryName);
        return category.getId();
    }

    // 카테고리 삭제
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
