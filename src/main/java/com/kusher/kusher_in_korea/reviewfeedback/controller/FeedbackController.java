package com.kusher.kusher_in_korea.reviewfeedback.controller;

import com.kusher.kusher_in_korea.reviewfeedback.domain.Feedback;
import com.kusher.kusher_in_korea.reviewfeedback.dto.CreateFeedbackDto;
import com.kusher.kusher_in_korea.reviewfeedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Long> createFeedback(@RequestBody CreateFeedbackDto createFeedbackDto) {
        Long feedbackId = feedbackService.createFeedback(createFeedbackDto);
        return ResponseEntity.ok(feedbackId);
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

}
