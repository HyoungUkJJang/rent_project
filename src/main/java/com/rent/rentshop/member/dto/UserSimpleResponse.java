package com.rent.rentshop.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSimpleResponse {

    private String userEmail;
    private String userName;

    @Builder
    public UserSimpleResponse(String userEmail, String userName) {
        this.userEmail = userEmail;
        this.userName = userName;
    }

}
