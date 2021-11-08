package com.rent.rentshop.error;

public class UserIdDuplicateException extends RuntimeException {

    public UserIdDuplicateException() {
        super("아이디가 중복입니다.");
    }

}
