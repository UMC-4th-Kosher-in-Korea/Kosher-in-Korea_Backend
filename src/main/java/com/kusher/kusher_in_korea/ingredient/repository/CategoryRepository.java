package com.kusher.kusher_in_korea.ingredient.repository;

import com.kusher.kusher_in_korea.ingredient.domain.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 카테고리 삭제
    void deleteById(Long id);

    // 전체 카테고리 조회
    List<Category> findAll();

    Optional<Category> findById(Long id);

    // 이름으로 카테고리 조회
    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}
