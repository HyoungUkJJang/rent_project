package com.rent.rentshop.member.service;

import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.UserUpdate;
import com.rent.rentshop.member.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("UserService 클래스")
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Nested
    @DisplayName("join 메소드는")
    class Describe_join {

        @Nested
        @DisplayName("가입할 유저가 있을 경우에")
        class Context_exist_user {

            String email;
            User result;
            @BeforeEach
            void prepare() {
                User user = createUser();
                result = userService.join(user);
                email = result.getEmail();
            }

            @Test
            @DisplayName("사용자 정보를 저장소에 저장 후 리턴합니다.")
            void It_return_save_user() {
                String findEmail = result.getEmail();
                assertEquals(email, findEmail);
            }
        }

    }

    /**
     * 테스트 유저를 생성합니다.
     * @return 테스트 유저 객체
     */
    private User createUser() {
        return User.builder()
                .email("mail@mail.com")
                .password("12345")
                .name("hyounguk")
                .phone("010")
                .birth("1996")
                .bankName("kakaoBank")
                .account("123-123")
                .userAddress(new Address("road", "detail"))
                .build();
    }

}
