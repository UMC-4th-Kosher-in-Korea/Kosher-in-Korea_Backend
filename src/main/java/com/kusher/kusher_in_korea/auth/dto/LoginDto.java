package com.kusher.kusher_in_korea.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto { // 로그인 요청

    private String userEmail; // 유저이메일
}
