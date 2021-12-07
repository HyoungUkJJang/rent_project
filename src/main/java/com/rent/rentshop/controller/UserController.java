package com.rent.rentshop.controller;

import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.UserRequest;
import com.rent.rentshop.member.dto.UserResponse;
import com.rent.rentshop.member.dto.UserUpdate;
import com.rent.rentshop.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 회원과 관련된 HTTP 요청을 수행하는 컨트롤러 클래스입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rent/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    /**
     * 회원들을 조회후 200 상태코드를 응답합니다.
     * @return 회원의 간략한 정보만 담고있는 리스트
     */
    @GetMapping
    public List<UserResponse.UserSimpleResponse> getUsers() {

        List<UserResponse.UserSimpleResponse> result = userService.getUsers()
                .stream()
                .map(u -> new UserResponse.UserSimpleResponse(
                        u.getEmail(),
                        u.getName()))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * 회원을 상세조회 하여 리턴 후 200 상태코드를 응답합니다.
     * @param userEmail 조회할 사용자 이메일
     * @return 조회된 사용자 상세조회 dto
     */
    @GetMapping("/{userEmail}")
    public UserResponse.UserDetailResponse getUser(@PathVariable("userEmail") String userEmail) {

        User findUser = userService.getUser(userEmail);
        return UserResponse.toDetailUser(findUser);

    }

    /**
     * 새로운 회원을 저장소에 저장 후 201 상태코드를 응답합니다.
     * @param form 저장할 사용자의 정보
     * @return 저장된 사용자 상세조회 dto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse.UserDetailResponse register(@RequestBody @Valid UserRequest form) {

        User userForm = User.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .birth(form.getBirth())
                .bankName(form.getBankName())
                .account(form.getAccount())
                .userAddress(new Address(form.getRoadAddress(), form.getDetailAddress()))
                .build();

        User joinUser = userService.join(userForm);

        return UserResponse.toDetailUser(joinUser);

    }

    /**
     * 사용자 정보를 수정합니다.
     * @param userEmail 수정할 사용자 이메일
     * @param form 수정할 사용자의 새로운 정보
     */
    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT}, value = "/{userEmail}")
    public void updateUser(@PathVariable("userEmail") String userEmail,
                           @RequestBody @Valid UserUpdate form) {

        userService.userUpdate(userEmail, form);

    }

    /**
     * 사용자를 삭제합니다.
     * @param userEmail 삭제할 사용자 이메일
     */
    @DeleteMapping("/{userEmail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userEmail) {
        userService.userDelete(userEmail);
    }

}
