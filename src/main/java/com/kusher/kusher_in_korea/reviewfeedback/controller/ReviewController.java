package com.kusher.kusher_in_korea.reviewfeedback.controller;

import com.kusher.kusher_in_korea.reviewfeedback.dto.CreateReviewDto;
import com.kusher.kusher_in_korea.reviewfeedback.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<Long> createReview(@RequestBody CreateReviewDto createReviewDto) {
        Long reviewId = reviewService.createReview(createReviewDto);
        return ResponseEntity.ok(reviewId);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

