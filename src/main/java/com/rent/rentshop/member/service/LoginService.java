package com.rent.rentshop.member.service;

import com.rent.rentshop.member.dto.LoginData;

public interface LoginService {

    String login(LoginData loginData);

    String parseToken(String accessToken);

}
