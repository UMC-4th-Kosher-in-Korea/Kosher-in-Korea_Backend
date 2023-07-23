package com.kusher.kusher_in_korea.reviewfeedback.domain;

import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.tabling.domain.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "review")
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id; // 평가번호

    @ManyToOne(fetch = FetchType.LAZY) // 유저 - 평가는 1대다
    @JoinColumn(name = "user_id")
    private User user; // 평가자(유저번호)

    @ManyToOne(fetch = FetchType.LAZY) // 식당 - 평가는 1대다
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant; // 식당(식당번호)

    private Long rating; // 평점

    private String review; // 평가내용

    private LocalDateTime reviewDateTime; // 평가날짜

}
