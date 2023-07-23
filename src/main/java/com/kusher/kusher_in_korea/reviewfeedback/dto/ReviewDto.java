package com.kusher.kusher_in_korea.reviewfeedback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDto { // 식당별 평가, 내가 한 평가 등을 클라이언트에게 전송할 목적
    private Long reviewId; // 평가번호
    private Long userId; // 유저번호
    private Long restaurantId; // 식당번호
    private String reviewContent; // 리뷰내용
    private String reviewTime; // 평가작성일시 -> LocalDateTime으로 변경될 수도 있음
    private Long reviewScore; // 평점
}
