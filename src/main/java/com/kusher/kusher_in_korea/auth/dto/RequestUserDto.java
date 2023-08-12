package com.kusher.kusher_in_korea.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestUserDto { // 유저 추가 요청
    private String userEmail; // 유저이메일
}
