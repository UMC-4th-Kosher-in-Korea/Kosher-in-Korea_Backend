package com.kusher.kusher_in_korea.ingredient.repository;

import com.kusher.kusher_in_korea.ingredient.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);

}
