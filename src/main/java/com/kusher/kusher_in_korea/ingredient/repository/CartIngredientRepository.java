package com.kusher.kusher_in_korea.ingredient.repository;

import com.kusher.kusher_in_korea.ingredient.domain.CartIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartIngredientRepository extends JpaRepository<CartIngredient, Long> {
    Optional<CartIngredient> findById(Long ingredientId);
}
