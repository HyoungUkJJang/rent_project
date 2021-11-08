package com.rent.rentshop.member.service;

import com.rent.rentshop.error.LoginFailException;
import com.rent.rentshop.error.UnauthorizedException;
import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.LoginData;
import com.rent.rentshop.member.repository.UserRepository;
import com.rent.rentshop.member.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("LoginServiceImpl 클래스")
class LoginServiceImplTest {

    private final String SECRET_KEY = "1234567890123456789012345678901234567890";
    private final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyRW1haWwiOiJtYWlsQG1haWwifQ.de5psC-sjGt9QTqwmYdOVxD4kc05ISE4GIOlhAXzCO4";
    private final String INVALID_TOKEN = VALID_TOKEN+"INVALID";

    private LoginService loginService;
    private UserService userService;
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        jwtUtil = new JwtUtil(SECRET_KEY);
        userService = new UserServiceImpl(userRepository);
        loginService = new LoginServiceImpl(userService, jwtUtil);
    }

    @Nested
    @DisplayName("login 메소드는")
    class Describe_login {

        @Nested
        @DisplayName("가입된 사용자가 아이디, 패스워드를 입력해 요청할 경우")
        class Context_input_loginData {

            LoginData loginDataTrue;
            LoginData loginDataFalse;

            @BeforeEach
            void prepare() {

                userService.join(createUser());

                User formUser = createUser();

                loginDataTrue = LoginData.builder()
                        .userEmail(formUser.getUserEmail())
                        .password(formUser.getPassword())
                        .build();
                loginDataFalse = LoginData.builder()
                        .userEmail(formUser.getUserEmail())
                        .password(formUser.getPassword()+"INVALID")
                        .build();

            }

            @Test
            @DisplayName("아이디 패스워드가 일치하면 토큰을 발급해준다.")
            void It_return_jwtToken() {
                String accessToken = loginService.login(loginDataTrue);
                assertEquals(accessToken,VALID_TOKEN);
            }

            @Test
            @DisplayName("아이디 패스워드가 일치하지 않는다면 로그인 실패 예외를 던진다.")
            void It_return_loginFailException() {
                assertThatThrownBy(() -> loginService.login(loginDataFalse))
                        .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다.")
                        .isInstanceOf(LoginFailException.class);

            }

        }

    }

    @Nested
    @DisplayName("parseToken 메소드는")
    class Describe_parseToken {

        @Nested
        @DisplayName("로그인 된 사용자 중 토큰이 유효할 경우")
        class Context_valid_token {

            LoginData loginData;
            String accessToken;
            @BeforeEach
            void prepare() {

                userService.join(createUser());

                User formUser = createUser();
                loginData = LoginData.builder()
                        .userEmail(formUser.getUserEmail())
                        .password(formUser.getPassword())
                        .build();
                accessToken = loginService.login(loginData);

            }

            @Test
            @DisplayName("토큰을 해독하여 유저 아이디를 반환한다.")
            void It_return_userId() {
                String expectToken = loginService.parseToken(accessToken);
                String actualToken = loginService.parseToken(VALID_TOKEN);
                assertEquals(expectToken, actualToken);
            }

        }

        @Nested
        @DisplayName("토큰이 유효하지 않을 경우")
        class Context_Invalid_token {

            @Test
            @DisplayName("토큰이 유효하지 않다는 UnauthorizedException 예외를 던진다.")
            void It_return_SignatureException() {
                assertThatThrownBy(() -> loginService.parseToken(INVALID_TOKEN))
                        .isInstanceOf(UnauthorizedException.class);
            }

        }

    }

    private User createUser() {
        User user = User.builder()
                .userEmail("mail@mail")
                .password("12345")
                .userName("name1")
                .userPhone("010")
                .userBirth("1996")
                .userAddress(new Address("road", "detail"))
                .build();
        return user;
    }

}