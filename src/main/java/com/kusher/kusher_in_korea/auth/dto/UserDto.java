package com.kusher.kusher_in_korea.auth.dto;

import com.kusher.kusher_in_korea.auth.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id; // 유저번호
    private String userEmail; // 유저이메일
    private String userType; // 유저유형 (일반유저 or 식당주인)

    public UserDto(User user){
        this.id = user.getId();
        this.userEmail = user.getUserEmail();
        this.userType = user.getUserType();
    }
}
