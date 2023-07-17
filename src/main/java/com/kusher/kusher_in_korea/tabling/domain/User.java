package com.kusher.kusher_in_korea.tabling.domain;

import com.kusher.kusher_in_korea.tabling.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 유저번호

    private String userName; // 유저이름

    private String userPhone; // 전화번호

    private String userType; // 유저유형 (일반유저 or 식당주인)

    // 생성 메서드
    public static User createUser(String userName, String userPhone, String userType) {
        User user = new User();
        user.setUserName(userName);
        user.setUserPhone(userPhone);
        user.setUserType(userType);
        return user;
    }

    public static User createUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setUserPhone(userDto.getUserPhone());
        user.setUserType(userDto.getUserType());
        return user;
    }

    // 비즈니스 로직
    // 유저 정보 수정
    public void updateUser(String userName, String userPhone, String userType) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userType = userType;
    }

    public void updateUser(UserDto userDto) {
        this.userName = userDto.getUserName();
        this.userPhone = userDto.getUserPhone();
        this.userType = userDto.getUserType();
    }

}
