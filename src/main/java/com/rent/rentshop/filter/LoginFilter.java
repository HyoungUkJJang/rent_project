package com.rent.rentshop.filter;

import com.rent.rentshop.error.InvalidTokenException;
import com.rent.rentshop.member.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends BasicAuthenticationFilter {

    private final LoginService loginService;

    public LoginFilter(AuthenticationManager authenticationManager, LoginService loginService) {
        super(authenticationManager);
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (filterWithPathAndMethod(request)) {
            chain.doFilter(request, response);
            return ;
        }

        String authorization = request.getHeader("Authentication");

        if (authorization == null) {
            throw new InvalidTokenException();
        }

        String accessToken = authorization.substring("Bearer ".length());
        loginService.parseToken(accessToken);

        chain.doFilter(request, response);

    }

    private boolean filterWithPathAndMethod(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return true;
        }
        if (request.getRequestURI().startsWith("/rent/user") || request.getRequestURI().startsWith("/rent/login")) {
            return true;
        }
        return false;
    }

}
