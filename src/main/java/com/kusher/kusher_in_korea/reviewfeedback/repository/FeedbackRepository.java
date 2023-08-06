package com.kusher.kusher_in_korea.reviewfeedback.repository;

import com.kusher.kusher_in_korea.reviewfeedback.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
