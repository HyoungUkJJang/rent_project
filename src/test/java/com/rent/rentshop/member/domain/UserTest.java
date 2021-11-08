package com.rent.rentshop.member.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void prepare() {
        user = User.builder()
                .userEmail("mail@mail")
                .userName("name1")
                .userPhone("010")
                .userBirth("1996")
                .userAddress(new Address("road", "detail"))
                .build();
    }

    @Test
    @DisplayName("사용자 생성 테스트")
    public void 사용자생성() throws Exception {

        //GIVEN
        String expectedUserEmail = "mail@mail";

        //WHEN
        String actualUserEmail = user.getUserEmail();

        //THEN
        assertEquals(expectedUserEmail,actualUserEmail);

    }

    @Test
    @DisplayName("사용자 수정 테스트")
    public void 사용자수정() throws Exception {

        //GIVEN
        String updateUserMail = "update@mail";
        String updateUserPhone = "011";
        String updateRoadAddress = "updateRoad";
        String updateDetailAddress = "updateDetail";
        user.updateUser(updateUserMail, updateUserPhone,
                updateRoadAddress, updateDetailAddress);

        //WHEN
        String resultUserMail = user.getUserEmail();

        //THEN
        assertEquals(updateUserMail, resultUserMail);

    }

    @Test
    @DisplayName("패스워드 암호화 테스트")
    public void 패스워드암호화() throws Exception {

        //GIVEN
        String rawPassword = "12345";
        user.createPassword(rawPassword, passwordEncoder);

        //WHEN
        String secretPassword = user.getPassword();

        //THEN
        Assertions.assertThat(secretPassword.equals(rawPassword)).isFalse();

    }

    @Test
    @DisplayName("패스워드 복호화 테스트")
    public void 패스워드복호화() throws Exception {

        //GIVEN
        String rawPassword = "12345";

        //WHEN
        user.createPassword(rawPassword, passwordEncoder);

        //THEN
        Assertions.assertThat(user.passwordCheck(rawPassword, passwordEncoder)).isTrue();

    }

}
