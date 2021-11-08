package com.rent.rentshop.error;

public class UserEmailDuplicateException extends RuntimeException {

    public UserEmailDuplicateException() {
        super("이메일이 중복입니다.");
    }

}
