package com.kusher.kusher_in_korea.tabling.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserDto { // 유저정보 수정 요청
    private Long userId; // 유저번호

    private String userPhone; // 전화번호

    private String userType; // 유저유형 (일반유저 or 식당주인)

}
