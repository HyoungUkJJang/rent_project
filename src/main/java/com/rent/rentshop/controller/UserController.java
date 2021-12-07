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

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

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

    @GetMapping("/{userEmail}")
    public UserResponse.UserDetailResponse getUser(@PathVariable("userEmail") String userEmail) {

        User findUser = userService.getUser(userEmail);
        return UserResponse.toDetailUser(findUser);

    }

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
