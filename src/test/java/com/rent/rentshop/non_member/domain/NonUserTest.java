package com.rent.rentshop.non_member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonUserTest {

    @Test
    @DisplayName("비회원 유저 정보를 생성합니다.")
    public void 비회원_유저_생성() {
        // GIVEN
        NonUser nonUser = new NonUser("김형욱", "010-0000-0000");

        // WHEN
        String name = "김형욱";

        // THEN
        assertEquals(name, nonUser.getName());
    }

}
