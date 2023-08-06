package com.kusher.kusher_in_korea.reviewfeedback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class CreateReviewDto { // 평가 생성 요청
    private Long userId; // 유저번호
    private Long restaurantId; // 식당번호
    private MultipartFile reviewImage; // 식당이미지
    private String contents; // 리뷰내용
    private int rating; // 평점
}
