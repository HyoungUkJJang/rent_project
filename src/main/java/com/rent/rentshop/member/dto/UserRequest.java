package com.rent.rentshop.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 회원가입 요청 도메인
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequest {

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;
    @NotEmpty(message = "패스워드는 필수입니다.")
    private String password;
    @NotEmpty(message = "이름은 필수입니다.")
    private String name;
    @NotEmpty(message = "휴대폰 번호는 필수입니다.")
    private String phone;
    @NotEmpty(message = "은행 이름은 필수입니다.")
    private String bankName;
    @NotEmpty(message = "계좌번호는 필수입니다.")
    private String account;
    @NotEmpty(message = "도로명 주소는 필수입니다.")
    private String roadAddress;
    @NotEmpty(message = "상세 주소는 필수입니다.")
    private String detailAddress;

    private String birth;

    @Builder
    public UserRequest(String email, String password, String name, String phone, String bankName, String account, String roadAddress, String detailAddress, String birth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.bankName = bankName;
        this.account = account;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.birth = birth;
    }

}
