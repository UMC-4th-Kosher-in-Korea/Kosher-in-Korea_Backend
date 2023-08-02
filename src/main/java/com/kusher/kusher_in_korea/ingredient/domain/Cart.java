package com.kusher.kusher_in_korea.ingredient.domain;

import com.kusher.kusher_in_korea.auth.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "cart")
public class Cart { // User와 장바구니는 일대일 관계
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(mappedBy = "cart",fetch = FetchType.LAZY)
    private User user; // 장바구니를 소유한 회원
}
