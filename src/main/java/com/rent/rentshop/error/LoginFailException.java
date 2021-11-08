package com.rent.rentshop.error;

public class LoginFailException extends RuntimeException {
    public LoginFailException() {
        super("아이디 또는 비밀번호가 일치하지 않습니다.");
    }
}
