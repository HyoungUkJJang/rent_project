package com.rent.rentshop.controller;

import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.UserRequest;
import com.rent.rentshop.member.dto.UserResponse;
import com.rent.rentshop.member.dto.UserSimpleResponse;
import com.rent.rentshop.member.dto.UserUpdate;
import com.rent.rentshop.member.service.UserService;
import com.rent.rentshop.common.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseData getUsers() {
        List<UserSimpleResponse> result = userService.getUsers()
                .stream()
                .map(u -> new UserSimpleResponse(
                        u.getUserEmail(),
                        u.getUserName()))
                .collect(Collectors.toList());

        return new ResponseData(result);
    }

    @GetMapping("/{userEmail}")
    public ResponseData getUser(@PathVariable("userEmail") String userEmail) {

        User findUser = userService.getUser(userEmail);

        UserResponse result = UserResponse.builder()
                .userEmail(findUser.getUserEmail())
                .userName(findUser.getUserName())
                .userPhone(findUser.getUserPhone())
                .userBirth(findUser.getUserBirth())
                .roadAddress(findUser.getUserAddress().getRoadAddress())
                .detailAddress(findUser.getUserAddress().getDetailAddress())
                .build();

        return new ResponseData(result);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData register(@RequestBody @Valid UserRequest form) {

        User userForm = User.builder()
                .userEmail(form.getUserEmail())
                .password(form.getPassword())
                .userName(form.getUserName())
                .userPhone(form.getUserPhone())
                .userBirth(form.getUserBirth())
                .userAddress(new Address(form.getRoadAddress(), form.getDetailAddress()))
                .build();

        User joinUser = userService.join(userForm);

        UserResponse result = UserResponse.builder()
                .userEmail(joinUser.getUserEmail())
                .userName(joinUser.getUserName())
                .userPhone(joinUser.getUserPhone())
                .userBirth(joinUser.getUserBirth())
                .roadAddress(joinUser.getUserAddress().getRoadAddress())
                .detailAddress(joinUser.getUserAddress().getDetailAddress())
                .build();

        return new ResponseData(result);

    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT}, value = "/{userId}")
    public void updateUser(@PathVariable("userId") String userId,
                           @RequestBody @Valid UserUpdate form) {

        userService.userUpdate(userId, form);

    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        userService.userDelete(userId);
    }

}
