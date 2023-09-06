package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.security.jwt.JwtToken;
import com.dongguk.ossdev.backend.service.AuthService;
import com.dongguk.ossdev.backend.domain.type.LoginProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/kakao")
    public void getKakaoRedirectURL(HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.getRedirectUrl(LoginProvider.KAKAO));
    }

    @GetMapping("/kakao/callback")
    public void loginByKakao(HttpServletResponse response,
                                          @RequestParam("code") String authCode) throws IOException {
        JwtToken jwtToken = authService.login(authCode, LoginProvider.KAKAO).getJwt();
        authService.sendRedirectToFront(response, jwtToken);
    }

    @GetMapping("/naver")
    public void getNaverRedirectURL(HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.getRedirectUrl(LoginProvider.NAVER));
    }

    @GetMapping("/naver/callback")
    public void loginByNaver(HttpServletResponse response,
                                          @RequestParam("code") String authCode) throws IOException {
        JwtToken jwtToken = authService.login(authCode, LoginProvider.NAVER).getJwt();
        authService.sendRedirectToFront(response, jwtToken);
    }

}
