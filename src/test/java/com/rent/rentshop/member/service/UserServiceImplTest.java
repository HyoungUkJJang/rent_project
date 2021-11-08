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
    @DisplayName("getUsers 메소드는")
    class Describe_getUsers {

        @Nested
        @DisplayName("사용자를 전체 조회하고 싶을 경우에")
        class Context_userList {

            @BeforeEach
            void prepare() {
                for (int i = 0; i < 5; i++) {
                    User userForm = User.builder()
                            .userEmail("mail" + i)
                            .password("password"+1)
                            .userName("name" + i)
                            .userPhone("phone" + i)
                            .userBirth("1990" + i)
                            .userAddress(new Address("111-111" + i, "address1" + i))
                            .build();
                    userService.join(userForm);
                }
            }

            @Test
            @DisplayName("사용자 목록을 리턴한다.")
            void It_return_userList() {
                List<User> result = userService.getUsers();
                assertThat(result).hasSize(5);
            }

        }

        @Nested
        @DisplayName("사용자가 한명도 경우에")
        class Context_users_notFound {

            @BeforeEach
            void prepare() {
                userRepository.findAll().clear();
            }

            @Test
            @DisplayName("비어있는 리스트를 반환한다.")
            void It_return_emptyList() {

                assertThat(userService.getUsers()).isEmpty();

            }

        }

    }

    @Nested
    @DisplayName("getUser 메소드는")
    class Describe_getUser {

        @Nested
        @DisplayName("상세 조회할 사용자가 있을 경우에")
        class Context_exist_user {

            String userEmail;
            User user;

            @BeforeEach
            void prepare() {
                user = userService.join(createUser());
                userEmail = user.getUserEmail();
            }

            @Test
            @DisplayName("사용자 아이디를 이용하여 정보를 조회해 리턴합니다.")
            void It_return_user() {
                User result = userService.getUser(userEmail);
                assertEquals(user.getId(),result.getId());
            }

        }

        @Nested
        @DisplayName("상세 조회할 사용자가 없을 경우에")
        class Context_user_notFound {

            String userId = "INVALID_ID";

            @BeforeEach
            void prepare() {
                userRepository.findAll().clear();
            }

            @Test
            @DisplayName("UserNotFoundException 예외를 던진다.")
            void It_return_userNotFoundException() {

                assertThatThrownBy(() -> userService.getUser(userId))
                        .hasMessage("사용자를 찾을 수 없습니다.")
                        .isInstanceOf(UserNotFoundException.class);

            }

        }

    }

    @Nested
    @DisplayName("join 메소드는")
    class Describe_join {

        @Nested
        @DisplayName("가입할 사용자가 있을 경우에")
        class Context_exist_join_user {

            User form;

            @BeforeEach
            void prepare() {
                form = createUser();
            }

            @Test
            @DisplayName("저장소에 사용자를 저장 후 리턴한다.")
            void It_save_return_user() {

                User result = userService.join(form);
                assertEquals(form.getUserName(), result.getUserName());

            }

        }

    }

    @Nested
    @DisplayName("userEmailCheck 메소드는")
    class Describe_userIdEmail {

        @Nested
        @DisplayName("가입할 사용자의 이메일이 중복일 경우")
        class Context_userEmail_duplicate {

            String userEmail;

            @BeforeEach
            void prepare() {
                User form = createUser();
                User result = userService.join(form);
                userEmail = result.getUserEmail();
            }

            @Test
            @DisplayName("true를 리턴한다.")
            void It_return_test() {
                assertThat(userService.userEmailCheck(userEmail)).isTrue();
            }

        }

        @Nested
        @DisplayName("가입할 사용자의 아이디가 중복일 아닐 경우")
        class Context_userEmail_notDuplicate {

            String userEmail;

            @BeforeEach
            void prepare() {
                User form = createUser();
                User result = userService.join(form);
                userEmail = result.getUserEmail() + "VALID";
            }

            @Test
            @DisplayName("false를 리턴한다.")
            void It_return_test() {
                assertThat(userService.userEmailCheck(userEmail)).isFalse();
            }

        }

    }

    @Nested
    @DisplayName("userUpdate 메소드는")
    class Describe_userUpdate {

        @Nested
        @DisplayName("수정할 사용자 정보가 있을 경우에")
        class Context_exist_updateUser {

            String userEmail;
            User user;
            UserUpdate updateForm;

            @BeforeEach
            void prepare() {

                user = userService.join(createUser());
                userEmail = user.getUserEmail();

                updateForm = UserUpdate.builder()
                        .password("update12345")
                        .userEmail("update@mail")
                        .userPhone("011")
                        .roadAddress("999-999")
                        .detailAddress("updateAddress")
                        .build();

            }

            @Test
            @DisplayName("사용자 요청 도메인을 받아 사용자 정보를 수정한다.")
            void It_update_user() {

                userService.userUpdate(userEmail, updateForm);
                assertEquals(user.getUserEmail(), updateForm.getUserEmail());

            }

        }

    }

    @Nested
    @DisplayName("userDelete 메소드는")
    class Describe_userDelete {

        @Nested
        @DisplayName("삭제할 사용자가 있을 경우에")
        class Context_exist_user {

            String userEmail;

            @BeforeEach
            void prepare() {
                userRepository.findAll().clear();

                User form = createUser();
                User result = userService.join(form);

                userEmail = result.getUserEmail();

            }

            @Test
            @DisplayName("저장소에서 사용자를 삭제한다.")
            void It_delete_user() {
                userService.userDelete(userEmail);
                assertThat(userRepository.findAll()).isEmpty();
            }

        }

        @Nested
        @DisplayName("삭제할 사용자를 찾지 못할 경우에")
        class Context_user_notFound {

            String userId = "INVALID";

            @BeforeEach
            void prepare() {
                userRepository.findAll().clear();
            }

            @Test
            @DisplayName("UserNotFoundException 예외를 던진다.")
            void It_return_userNotFoundException() {
                assertThatThrownBy(() -> userService.userDelete(userId))
                        .hasMessage("사용자를 찾을 수 없습니다.")
                        .isInstanceOf(UserNotFoundException.class);
            }

        }

    }

    private User createUser() {
        User user = User.builder()
                .userEmail("mail1")
                .password("1234")
                .userName("name1")
                .userPhone("010")
                .userBirth("1996")
                .userAddress(new Address("111-111", "address1"))
                .build();
        return user;
    }

}
