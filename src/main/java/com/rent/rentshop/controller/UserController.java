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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseData getUsers() {
        List<UserSimpleResponse> result = userService.getUsers()
                .stream()
                .map(u -> new UserSimpleResponse(
                        u.getEmail(),
                        u.getName()))
                .collect(Collectors.toList());

        return new ResponseData(result);
    }

    @GetMapping("/{userEmail}")
    public ResponseData getUser(@PathVariable("userEmail") String userEmail) {

        User findUser = userService.getUser(userEmail);

        UserResponse result = UserResponse.builder()
                .email(findUser.getEmail())
                .name(findUser.getName())
                .phone(findUser.getPhone())
                .birth(findUser.getBirth())
                .roadAddress(findUser.getUserAddress().getRoadAddress())
                .detailAddress(findUser.getUserAddress().getDetailAddress())
                .build();

        return new ResponseData(result);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData register(@RequestBody @Valid UserRequest form) {

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

        UserResponse result = UserResponse.builder()
                .email(joinUser.getEmail())
                .name(joinUser.getName())
                .phone(joinUser.getPhone())
                .birth(joinUser.getBirth())
                .roadAddress(joinUser.getUserAddress().getRoadAddress())
                .detailAddress(joinUser.getUserAddress().getDetailAddress())
                .bankName(joinUser.getBankName())
                .account(joinUser.getAccount())
                .build();

        return new ResponseData(result);

    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT}, value = "/{userEmail}")
    public void updateUser(@PathVariable("userEmail") String userEmail,
                           @RequestBody @Valid UserUpdate form) {

        userService.userUpdate(userEmail, form);

    }

    @DeleteMapping("/{userEmail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userEmail) {
        userService.userDelete(userEmail);
    }

}
