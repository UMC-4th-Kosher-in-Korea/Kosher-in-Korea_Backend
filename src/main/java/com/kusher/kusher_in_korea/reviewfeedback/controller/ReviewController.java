package com.kusher.kusher_in_korea.reviewfeedback.controller;

import com.kusher.kusher_in_korea.image.service.ImageUploadService;
import com.kusher.kusher_in_korea.reviewfeedback.dto.CreateReviewDto;
import com.kusher.kusher_in_korea.reviewfeedback.service.ReviewService;
import com.kusher.kusher_in_korea.util.api.ApiResponse;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ImageUploadService imageUploadService;

    // 리뷰 생성
    @PostMapping
    public ApiResponse<Long> createReview(CreateReviewDto createReviewDto) throws IOException {
        String imageUrl = imageUploadService.uploadImage(createReviewDto.getReviewImage());
        Long reviewId = reviewService.createReview(createReviewDto, imageUrl);
        return ApiResponse.success(reviewId, ResponseCode.REVIEW_CREATE_SUCCESS.getMessage());
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ApiResponse<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ApiResponse.success(null, ResponseCode.REVIEW_DELETE_SUCCESS.getMessage());
    }
}

