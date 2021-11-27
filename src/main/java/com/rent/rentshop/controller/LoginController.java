package com.rent.rentshop.controller;

import com.rent.rentshop.member.dto.LoginData;
import com.rent.rentshop.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rent/login")
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public String login(@RequestBody @Valid LoginData loginData) {
        return loginService.login(loginData);
    }

}
