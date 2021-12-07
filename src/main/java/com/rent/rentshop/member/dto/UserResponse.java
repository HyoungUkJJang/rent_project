package com.rent.rentshop.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 상세조회
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

    private String email;
    private String name;
    private String phone;
    private String birth;
    private String roadAddress;
    private String detailAddress;
    private String bankName;
    private String account;

    @Builder
    public UserResponse(String email, String name, String phone, String birth, String roadAddress, String detailAddress, String bankName, String account) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.bankName = bankName;
        this.account = account;
    }

}
