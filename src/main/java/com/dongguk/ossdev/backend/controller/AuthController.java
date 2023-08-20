package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.service.AuthService;
import com.dongguk.ossdev.backend.domain.type.LoginProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/kakao")
    public void getKakaoRedirectURL(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect(authService.getRedirectUrl(LoginProvider.KAKAO));
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> loginByKakao(@RequestParam("code") String authCode) {
        return ResponseEntity.ok(authService.login(authCode, LoginProvider.KAKAO));
    }

    @GetMapping("/naver")
    public void getNaverRedirectURL(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect(authService.getRedirectUrl(LoginProvider.NAVER));
    }

    @GetMapping("/naver/callback")
    public ResponseEntity<?> loginByNaver(@RequestParam("code") String authCode) {
        return ResponseEntity.ok(authService.login(authCode, LoginProvider.NAVER));
    }
}
