package com.kusher.kusher_in_korea.reviewfeedback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateReviewDto { // 평가 생성 요청
    private Long userId; // 유저번호
    private Long restaurantId; // 식당번호
    private String image; // 식당이미지
    private String contents; // 리뷰내용
    private String reviewDateTime; // 평가작성일시 -> LocalDateTime으로 변경될 수도 있음
    private Long rating; // 평점

}
