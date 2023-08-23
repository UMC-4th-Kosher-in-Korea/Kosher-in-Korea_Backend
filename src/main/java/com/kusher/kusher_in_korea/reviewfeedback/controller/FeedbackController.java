package com.kusher.kusher_in_korea.reviewfeedback.controller;

import com.kusher.kusher_in_korea.reviewfeedback.domain.Feedback;
import com.kusher.kusher_in_korea.reviewfeedback.dto.CreateFeedbackDto;
import com.kusher.kusher_in_korea.reviewfeedback.service.FeedbackService;
import com.kusher.kusher_in_korea.util.api.ApiResponse;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ApiResponse<Long> createFeedback(@RequestBody CreateFeedbackDto createFeedbackDto) {
        Long feedbackId = feedbackService.createFeedback(createFeedbackDto);
        return ApiResponse.success(feedbackId, ResponseCode.FEEDBACK_CREATE_SUCCESS.getMessage());
    }

    @GetMapping
    public ApiResponse<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ApiResponse.success(feedbacks, ResponseCode.FEEDBACK_READ_SUCCESS.getMessage());
    }

}
