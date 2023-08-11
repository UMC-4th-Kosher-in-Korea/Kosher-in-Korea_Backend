package com.kusher.kusher_in_korea.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.auth.dto.GoogleTokenRequest;
import com.kusher.kusher_in_korea.auth.dto.GoogleTokenResponse;
import com.kusher.kusher_in_korea.auth.dto.GoogleUserInfo;
import com.kusher.kusher_in_korea.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleOauthService {
    @Value("${oauth.google.client-id}")
    private String googleClientId;
    @Value("${oauth.google.client-secret}")
    private String googleClientSecret;
    @Value("${oauth.google.redirect-uri}")
    private String googleRedirectUrl;

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;
    private final UserRepository userRepository;

    @PostConstruct
    void init() {
        objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
    }

    public void loginByGoogle(String authorizationCode) throws IOException {
        // 1. AuthorizationCode 와 AccessToken 교환
        GoogleTokenResponse googleToken = getGoogleToken(authorizationCode);

        // 2. AccessToken 이용 사용자 정보 획득
        GoogleUserInfo googleUserInfo = getGoogleUserInfo(googleToken.getAccessToken());

        // 3. 유저 정보가 DB에 있을 경우 로그인 진행
        // todo. User테이블에 id 컬럼에 대해서
        Optional<User> optionalUser = userRepository.findById(googleUserInfo.getId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // todo. 유저 정보를 활용한 처리, 로그인, jwt 토큰?
        } else {
            // todo. 해당 ID에 맞는 유저가 없을 때의 처리
        }
        // 4. 유저 정보가 없을 경우 회원 가입 진행
    }

    /** Authorization Code 이용 Google Token 발급 */
    private GoogleTokenResponse getGoogleToken(String authorizationCode)
            throws JsonProcessingException {
        GoogleTokenRequest googleTokenRequest = GoogleTokenRequest
                .create(authorizationCode, googleClientId, googleClientSecret, googleRedirectUrl);

        URI getTokenUri = UriComponentsBuilder
                .fromUriString("https://oauth2.googleapis.com")
                .path("/token").encode().build().toUri();

        ResponseEntity<String> googleResponse =
                restTemplate.postForEntity(getTokenUri, googleTokenRequest, String.class);
        return objectMapper.readValue(googleResponse.getBody(), GoogleTokenResponse.class);
    }

    /** Token 이용 사용자 정보 조회 */
    private GoogleUserInfo getGoogleUserInfo(String accessToken) throws JsonProcessingException {
        URI getTokenInfoUri = UriComponentsBuilder
                .fromUriString("https://www.googleapis.com")
                .path("/oauth2/v2/userinfo")
                .queryParam("access_token", accessToken).encode().build().toUri();

        ResponseEntity<String> googleTokenInfoResponse =
                restTemplate.getForEntity(getTokenInfoUri, String.class);

        return objectMapper.readValue(googleTokenInfoResponse.getBody(), GoogleUserInfo.class);
    }
}