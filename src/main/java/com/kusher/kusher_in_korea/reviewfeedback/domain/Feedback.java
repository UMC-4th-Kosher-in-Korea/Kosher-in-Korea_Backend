package com.kusher.kusher_in_korea.reviewfeedback.domain;

import com.kusher.kusher_in_korea.auth.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "feedback")
public class Feedback {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id; // 피드백번호

    @ManyToOne(fetch = FetchType.LAZY) // 유저 - 피드백은 1대다
    @JoinColumn(name = "user_id")
    private User user; // 피드백자(유저번호)

    private String feedback; // 피드백내용

    private LocalDateTime feedbackDateTime; // 피드백날짜

}
