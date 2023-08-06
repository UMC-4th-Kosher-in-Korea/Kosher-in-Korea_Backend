package com.kusher.kusher_in_korea.reviewfeedback.service;


import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.auth.repository.UserRepository;
import com.kusher.kusher_in_korea.reviewfeedback.domain.Feedback;
import com.kusher.kusher_in_korea.reviewfeedback.dto.CreateFeedbackDto;
import com.kusher.kusher_in_korea.reviewfeedback.repository.FeedbackRepository;
import com.kusher.kusher_in_korea.util.exception.CustomException;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    // 피드백 생성
    public Long createFeedback(CreateFeedbackDto createFeedbackDto) {
        User user = userRepository.findById(createFeedbackDto.getUserId())
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setFeedback(createFeedbackDto.getFeedbackContent());
        Feedback savedFeedback = feedbackRepository.save(feedback);

        return savedFeedback.getId();
    }

    // 모든 피드백 조회
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

}

