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

}