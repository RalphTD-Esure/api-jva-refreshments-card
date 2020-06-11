package com.project.refreshments.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtProvider jwtProvider;

    public JwtSecurityConfigurer(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter customFilter = new JwtAuthenticationFilter(jwtProvider);
        http.exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint()).and()
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}