package com.kusher.kusher_in_korea.auth.controller;

import com.kusher.kusher_in_korea.auth.service.GoogleOauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GoogleOauthController {

    private final GoogleOauthService googleOauthService;

    @GetMapping("/oauth/google")
    public void authGoogleCode(@RequestParam String code) throws IOException {
        googleOauthService.loginByGoogle(code);
    }
}
