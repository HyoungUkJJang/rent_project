package com.rent.rentshop.non_member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Embeddable
public class NonUser {

    private String name;
    private String phone;

    @Builder
    public NonUser(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

}
