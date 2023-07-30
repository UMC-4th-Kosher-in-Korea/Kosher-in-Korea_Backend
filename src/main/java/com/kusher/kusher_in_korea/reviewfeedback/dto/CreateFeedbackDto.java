package com.kusher.kusher_in_korea.reviewfeedback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateFeedbackDto { // 앱 개발자들에게 피드백을 전송할 목적, 그러므로 FeedbackDto는 필요없음
    private Long userId; // 유저번호
    private String feedbackContent; // 피드백 내용
    private String feedbackTime; // 피드백 작성일시 -> LocalDateTime으로 변경될 수도 있음
}
