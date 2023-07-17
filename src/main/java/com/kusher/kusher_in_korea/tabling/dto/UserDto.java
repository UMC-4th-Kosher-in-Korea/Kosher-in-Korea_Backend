package com.kusher.kusher_in_korea.tabling.dto;

import com.kusher.kusher_in_korea.tabling.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id; // 유저번호
    private String userName; // 유저이름
    private String userPhone; // 전화번호
    private String userType; // 유저유형 (일반유저 or 식당주인)

    public UserDto(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userPhone = user.getUserPhone();
        this.userType = user.getUserType();
    }
}
