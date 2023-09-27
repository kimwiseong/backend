package com.dongguk.ossdev.backend.security.jwt;

import com.dongguk.ossdev.backend.repository.UserRepository;
import com.dongguk.ossdev.backend.domain.type.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider implements InitializingBean {
    private final UserRepository userRepository;
    @Value("${jwt.secretKey}")
    private String secretKey;
    private Key key;
    private static final Long accessExpiredMs = 60 * 60 * 2 * 1000l;
    private static final Long refreshExpiredMs = 60 * 60 * 24 * 60 * 1000l;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * token 생성
     */
    private String createToken(Long id, UserRole userRole, boolean isAccess) {
        Claims claims = Jwts.claims();

        claims.put("id", id);
        if(isAccess) {
            claims.put("userRoleType", userRole);
        }

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (isAccess ? accessExpiredMs : refreshExpiredMs)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * accessToken, refreshToken 생성
     */
//    public Map<String, String> createTotalToken(Long id, EUserRole role) {
//        Map<String, String> map = new HashMap<>();
//        map.put("access_token", createToken(id, role, true));
//        map.put("refresh_token", createToken(id, role, false));
//
//        return map;
//    }

    /**
     * accessToken, refreshToken 생성
     */
    public JwtToken createTotalToken(Long id, UserRole userRoleType) {

        //Access token 생성
        String accessToken = createToken(id, userRoleType, true);

        //Refresh token 생성
        String refreshToken = createToken(id, userRoleType, false);

        return JwtToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * refreshToken validation 체크(refresh token 이 넘어왔을때)
     * 정상 - access 토큰 생성후 반환
     * 비정상 - null
     */
//    public String validRefreshToken(HttpServletRequest request) throws JwtException {
//
//        String refreshToken = refineToken(request);
//
//        Claims claims = validateToken(refreshToken);
//
//        // 예외 처리 필요함
//        UserRepository.UserLoginForm user = userRepository.findByIdAndRefreshToken(Long.valueOf(claims.get("id").toString()), refreshToken).get();
//
//        return createToken(user.getId(), user.getRole(), true);
//    }

    public String getUserId(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("id").toString();
    }

    // 토큰의 유효성 + 만료일자 확인
    public Claims validateToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String refineToken(HttpServletRequest request) throws JwtException {
        String beforeToken = request.getHeader("Authorization");

        String afterToken = null;
        if (StringUtils.hasText(beforeToken) && beforeToken.startsWith("Bearer ")) {
            afterToken =  beforeToken.substring(7);
        } else {
            throw new IllegalArgumentException("Not Valid Or Not Exist Token");
        }

        return afterToken;
    }
}