package com.kusher.kusher_in_korea.ingredient.repository;

import com.kusher.kusher_in_korea.ingredient.domain.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> { // 식재료 테이블을 관리
    // 식재료 삭제: delete
    void deleteById(Long id);

    // 전체 식재료 조회
    List<Ingredient> findAll();

    // 이름으로 식재료 조회
    Optional<Ingredient> findByName(String name);

    // 식재료 id로 식재료 조회
    Optional<Ingredient> findById(Long id);

    Page<Ingredient> findAllByCategoryId(Long categoryId, Pageable pageable);
}
