package com.rent.rentshop.config;

import com.rent.rentshop.filter.LoginErrorFilter;
import com.rent.rentshop.filter.LoginFilter;
import com.rent.rentshop.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.Filter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginService loginService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        Filter loginFilter = new LoginFilter(authenticationManager(), loginService);

        Filter loginErrorFilter = new LoginErrorFilter();

        http.csrf().disable()
                .addFilter(loginFilter)
                .addFilterBefore(loginErrorFilter, LoginFilter.class);

    }

}
