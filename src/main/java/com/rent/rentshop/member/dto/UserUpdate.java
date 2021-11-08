package com.rent.rentshop.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 사용자 수정 요청
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdate {

    @NotEmpty(message = "패스워드는 필수입니다.")
    private String password;
    @NotEmpty(message = "이메일은 필수입니다.")
    private String userEmail;
    @NotEmpty(message = "휴대폰 번호는 필수입니다.")
    private String userPhone;
    @NotEmpty(message = "도로명 주소는 필수입니다.")
    private String roadAddress;
    @NotEmpty(message = "상세 주소는 필수입니다.")
    private String detailAddress;

    @Builder
    public UserUpdate(String password, String userEmail, String userPhone, String roadAddress, String detailAddress) {
        this.password = password;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }

}
