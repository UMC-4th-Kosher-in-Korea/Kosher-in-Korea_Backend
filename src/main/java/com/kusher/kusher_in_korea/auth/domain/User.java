package com.kusher.kusher_in_korea.auth.domain;

import com.kusher.kusher_in_korea.auth.dto.UserDto;
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

    private String userPhone; // 전화번호

    private String userType; // 유저유형 (일반유저 or 식당주인)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart; // 유저와 장바구니는 일대일 관계

    // 생성 메서드
    public static User createUser(UserDto userDto) {
        User user = new User();
        user.userName = userDto.getUserName();
        user.userPhone = userDto.getUserPhone();
        user.userType = userDto.getUserType();
        return user;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // 비즈니스 로직
    // 유저 정보 수정
    public void updateUser(UserDto userDto) {
        this.userName = userDto.getUserName();
        this.userPhone = userDto.getUserPhone();
        this.userType = userDto.getUserType();
    }

}
