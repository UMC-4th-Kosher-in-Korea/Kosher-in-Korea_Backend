package com.kusher.kusher_in_korea.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class UpdateUserDto {

    private String userName; // 유저이름
    private String userPhone; // 유저전화번호
    private MultipartFile profileImage; // 유저 프로필 이미지 파일
    private String userAddress; // 유저 주소
}
