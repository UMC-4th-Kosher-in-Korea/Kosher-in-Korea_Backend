package com.kusher.kusher_in_korea.reviewfeedback.dto;

import com.kusher.kusher_in_korea.reviewfeedback.domain.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewDto { // 식당별 평가, 내가 한 평가 등을 클라이언트에게 전송할 목적
    private Long reviewId; // 평가번호
    private Long userId; // 유저번호
    private Long restaurantId; // 식당번호
    private String image; // 식당이미지
    private String reviewContent; // 리뷰내용
    private LocalDateTime reviewTime; // 평가작성일시
    private int reviewRating; // 평점

    public ReviewDto(Review review) {
        this.reviewId = review.getId();
        this.userId = review.getUser().getId();
        this.restaurantId = review.getRestaurant().getId();
        this.image = review.getImage();
        this.reviewContent = review.getContents();
        this.reviewTime = review.getReviewDateTime();
        this.reviewRating = review.getRating();
    }

}
