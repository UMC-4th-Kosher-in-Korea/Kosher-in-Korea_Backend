package com.kusher.kusher_in_korea.ingredient.controller;

import com.kusher.kusher_in_korea.image.service.ImageUploadService;
import com.kusher.kusher_in_korea.ingredient.dto.request.CreateIngredientDto;
import com.kusher.kusher_in_korea.ingredient.dto.request.RequestCategoryDto;
import com.kusher.kusher_in_korea.ingredient.dto.request.RequestIngredientDto;
import com.kusher.kusher_in_korea.ingredient.dto.response.CategoryDto;
import com.kusher.kusher_in_korea.ingredient.dto.response.IngredientDto;
import com.kusher.kusher_in_korea.ingredient.service.IngredientService;
import com.kusher.kusher_in_korea.util.api.ApiResponse;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;
    private final ImageUploadService imageUploadService;

    // 전체 식재료 조회 (페이징 필요)
    @GetMapping("/api/ingredient")
    public ApiResponse<Page<IngredientDto>> findAllIngredients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<IngredientDto> ingredients = ingredientService.findAllIngredients(PageRequest.of(page, size));
        return ApiResponse.success(ingredients, ResponseCode.INGREDIENT_READ_SUCCESS.getMessage());
    }

    // 특정 식재료 조회
    @GetMapping("/api/ingredient/{ingredientId}")
    public ApiResponse<IngredientDto> findIngredient(@PathVariable("ingredientId") Long ingredientId) {
        IngredientDto ingredient = ingredientService.findIngredient(ingredientId);
        return ApiResponse.success(ingredient, ResponseCode.INGREDIENT_READ_SUCCESS.getMessage());
    }

    // 전체 카테고리 조회
    @GetMapping("/api/category")
    public ApiResponse<List<CategoryDto>> findAllCategories() {
        List<CategoryDto> categories = ingredientService.findAllCategories();
        return ApiResponse.success(categories, ResponseCode.CATEGORY_READ_SUCCESS.getMessage());
    }

    // 특정 카테고리에 속한 식재료 조회
    @GetMapping("/api/category/{categoryId}/ingredient")
    public ApiResponse<Page<IngredientDto>> findIngredientsByCategory(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable("categoryId") Long categoryId) {
        Page<IngredientDto> ingredients = ingredientService.findIngredientsByCategory(PageRequest.of(page, size), categoryId);
        return ApiResponse.success(ingredients, ResponseCode.INGREDIENT_READ_SUCCESS.getMessage());
    }

    // 아래부터는 서버에서 사용하는 관리용 메서드에 대한 Controller

    // 식재료 추가
    @PostMapping("/api/ingredient")
    public ApiResponse<Long> addIngredient(CreateIngredientDto ingredientDto) throws IOException {
        String imageUrl = null;
        if (ingredientDto.getIngredientImage() != null) {
            imageUrl = imageUploadService.uploadImage(ingredientDto.getIngredientImage());
        }
        Long id = ingredientService.addIngredient(ingredientDto, imageUrl);
        return ApiResponse.success(id, ResponseCode.INGREDIENT_CREATE_SUCCESS.getMessage());
    }

    // 식재료 수정
    @PutMapping("/api/ingredient/{ingredientId}")
    public ApiResponse<Long> updateIngredient(@PathVariable("ingredientId") Long ingredientId, RequestIngredientDto requestIngredientDto) {
        Long id = ingredientService.updateIngredient(ingredientId, requestIngredientDto);
        return ApiResponse.success(id, ResponseCode.INGREDIENT_UPDATE_SUCCESS.getMessage());
    }

    // 식재료 삭제
    @DeleteMapping("/api/ingredient/{ingredientId}")
    public ApiResponse<Void> deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
        return ApiResponse.success(null, ResponseCode.INGREDIENT_DELETE_SUCCESS.getMessage());
    }

    // 카테고리 추가
    @PostMapping("/api/category")
    public ApiResponse<Long> addCategory(@RequestBody RequestCategoryDto requestCategoryDto) {
        Long id = ingredientService.addCategory(requestCategoryDto.getCategoryName());
        return ApiResponse.success(id, ResponseCode.CATEGORY_CREATE_SUCCESS.getMessage());
    }

    // 카테고리 수정
    @PutMapping("/api/category/{categoryId}")
    public ApiResponse<Long> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody RequestCategoryDto requestCategoryDto) {
        Long id = ingredientService.updateCategory(categoryId, requestCategoryDto.getCategoryName());
        return ApiResponse.success(id, ResponseCode.CATEGORY_UPDATE_SUCCESS.getMessage());
    }

    // 카테고리 삭제
    @DeleteMapping("/api/category/{categoryId}")
    public ApiResponse<Void> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        ingredientService.deleteCategory(categoryId);
        return ApiResponse.success(null, ResponseCode.CATEGORY_DELETE_SUCCESS.getMessage());
    }

}
