package com.kusher.kusher_in_korea.auth.domain;

import com.kusher.kusher_in_korea.auth.dto.RequestUserDto;
import com.kusher.kusher_in_korea.ingredient.domain.Cart;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 유저번호

    private String userEmail; // 유저이메일

    private String userType; // 유저유형 (유저 or 점주)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart; // 유저와 장바구니는 일대일 관계

    // 생성 메서드
    public static User createUser(RequestUserDto userDto) {
        User user = new User();
        user.userEmail = userDto.getUserEmail();
        user.userType = "유저";
        return user;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
