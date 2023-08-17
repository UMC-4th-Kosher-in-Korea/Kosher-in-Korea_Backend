package com.kusher.kusher_in_korea.auth.dto;

import com.kusher.kusher_in_korea.auth.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id; // 유저번호
    private String userName; // 유저이름
    private String userEmail; // 유저이메일
    private String userPhone; // 유저전화번호
    private String profileImage; // 유저 프로필 이미지
    private String userAddress; // 유저 주소
    private String userType; // 유저유형 (일반유저 or 식당주인)

    public UserDto(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userPhone = user.getUserPhone();
        this.profileImage = user.getProfileImage();
        this.userAddress = user.getUserAddress();
        this.userType = user.getUserType();
    }
}
