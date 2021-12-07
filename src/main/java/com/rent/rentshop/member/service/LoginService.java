package com.rent.rentshop.member.service;

import com.rent.rentshop.member.dto.LoginData;

/**
 * 사용자 로그인 관련 기능을 제공하는 서비스
 */
public interface LoginService {

    /**
     * 사용자 로그인을 검증하고 토큰을 발급합니다.
     * @param loginData 검증할 로그인 정보
     * @return jwt토큰 발급
     */
    String login(LoginData loginData);

    /**
     * JWT 토큰을 해독하여 유효한 회원인지 검증합니다.
     * @param accessToken 검증할 토큰
     * @return 검증이 된다면 회원 이메일을 리턴합니다.
     */
    String parseToken(String accessToken);

}
