package com.kusher.kusher_in_korea.ingredient.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kusher.kusher_in_korea.auth.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Orders { // 주문은 유저와 일대다 관계
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 주문한 유저

    private OrderStatus status; // 주문 상태

    private LocalDateTime orderDateTime; // 주문 날짜 및 시간

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery; // 주문과 배송은 일대일 관계
}
