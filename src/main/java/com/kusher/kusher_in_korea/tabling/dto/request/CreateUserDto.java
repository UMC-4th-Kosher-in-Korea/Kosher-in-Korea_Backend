package com.kusher.kusher_in_korea.tabling.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDto { // 유저 추가 요청

    private String userName; // 유저이름
    private String userPhone; // 전화번호
    private String userType; // 유저유형 (일반유저 or 식당주인)

}
