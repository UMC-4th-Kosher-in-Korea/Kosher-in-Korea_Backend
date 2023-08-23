package com.kusher.kusher_in_korea.auth.domain;

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

    private String userName; // 유저이름

    private String userEmail; // 유저이메일

    private String userPhone; // 유저전화번호

    private String userType; // 유저유형 (유저 or 점주)

    private String profileImage; // 유저 프로필 이미지

    private String userAddress; // 유저 주소

    private boolean isFirstLogin; // 첫 로그인 여부

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "cart_id")
    private Cart cart; // 유저와 장바구니는 일대일 관계

    // 생성 메서드
    public static User createUser(String userName, String userEmail, String userPhone, String profileImage, String userAddress) {
        User user = new User();
        user.userName = userName;
        user.userEmail = userEmail;
        user.userPhone = userPhone;
        user.userType = "user";
        user.profileImage = profileImage;
        user.userAddress = userAddress;
        user.isFirstLogin = true; // 회원가입 직후엔 첫 로그인이므로 true
        return user;
    }

    // 유저 정보 수정
    public void updateUser(String userName, String userPhone, String profileImage, String userAddress) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.profileImage = profileImage;
        this.userAddress = userAddress;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setNotFirstLogin() {
        this.isFirstLogin = false;
    }
}
