package com.rent.rentshop.member.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    public void 유저생성() {
        User user = User.builder()
                .email("mail@mail.com")
                .password("12345")
                .name("김형욱")
                .phone("01012341234")
                .birth("1996")
                .bankName("국민은행")
                .account("12341234")
                .userAddress(new Address("road", "detail"))
                .build();

        Assertions.assertEquals("mail@mail.com", user.getEmail());
    }

}
