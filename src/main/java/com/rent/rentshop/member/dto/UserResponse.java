package com.rent.rentshop.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String userEmail;
    private String userName;
    private String userPhone;
    private String userBirth;
    private String roadAddress;
    private String detailAddress;

    @Builder
    public UserResponse(String userEmail, String userName, String userPhone, String userBirth, String roadAddress, String detailAddress) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }

}
