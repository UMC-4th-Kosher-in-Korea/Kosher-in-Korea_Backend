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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    // 피드백 생성
    public Long createFeedback(CreateFeedbackDto createFeedbackDto) {
        User user = userRepository.findById(createFeedbackDto.getUserId())
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        Feedback feedback = Feedback.createFeedback(user, createFeedbackDto.getFeedbackContent());
        return feedbackRepository.save(feedback).getId();
    }

    // 모든 피드백 조회
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

}

