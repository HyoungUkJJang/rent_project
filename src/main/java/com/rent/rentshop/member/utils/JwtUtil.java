package com.rent.rentshop.member.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * JWT 토큰을 발급하고 복호화 기능을 제공하는 클래스
 */
@Component
public class JwtUtil {

    private Key key;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * JWT 토큰을 발급합니다.
     * @param userEmail 토큰 클레임에 담을 유저 이메일
     * @return 발급된 토큰
     */
    public String createToken(String userEmail) {

        String token = Jwts.builder()
                .signWith(key)
                .claim("userEmail", userEmail)
                .compact();
        return token;

    }

    /**
     * 토큰을 해독하여 클레임을 리턴합니다.
     * @param accessToken 검증할 토큰
     * @return 유저 정보가 담긴 클레임
     */
    public Claims parseToken(String accessToken) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        return claims;

    }

}
