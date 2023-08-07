package com.kusher.kusher_in_korea.reviewfeedback.controller;

import com.kusher.kusher_in_korea.image.service.ImageUploadService;
import com.kusher.kusher_in_korea.reviewfeedback.dto.CreateReviewDto;
import com.kusher.kusher_in_korea.reviewfeedback.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ImageUploadService imageUploadService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<Long> createReview(CreateReviewDto createReviewDto) throws IOException {
        String imageUrl = imageUploadService.uploadImage(createReviewDto.getReviewImage());
        Long reviewId = reviewService.createReview(createReviewDto, imageUrl);
        return ResponseEntity.ok(reviewId);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

