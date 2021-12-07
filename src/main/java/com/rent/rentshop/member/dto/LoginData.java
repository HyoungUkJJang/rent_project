package com.rent.rentshop.member.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class LoginData {

    @NotEmpty(message = "이메일은 필수 입력 항목입니다.")
    private String email;
    @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;

    @Builder
    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    LoginData() {}

}
