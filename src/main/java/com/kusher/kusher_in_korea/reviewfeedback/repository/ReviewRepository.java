package com.kusher.kusher_in_korea.reviewfeedback.repository;

import com.kusher.kusher_in_korea.reviewfeedback.domain.Review;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 식당에 대한 평가 조회
    List<Review> findByRestaurantId(Long restaurantId);

    // 특정 유저가 남긴 평가 조회
    List<Review> findByUserId(Long userId);

    // 특정 유저가 이미 특정 식당에 평가를 남겼는지 확인
    boolean existsByUserIdAndRestaurantId(Long userId, Long restaurantId);

    // 평가 삭제
    void deleteById(@NotNull Long reviewId);
}
