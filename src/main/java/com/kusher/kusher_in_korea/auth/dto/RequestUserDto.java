package com.kusher.kusher_in_korea.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestUserDto { // 회원가입 및 로그인 요청
    private String userEmail; // 유저이메일
}
