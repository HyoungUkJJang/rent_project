package com.rent.rentshop.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSimpleResponse {

    private String email;
    private String name;

    @Builder

    public UserSimpleResponse(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
