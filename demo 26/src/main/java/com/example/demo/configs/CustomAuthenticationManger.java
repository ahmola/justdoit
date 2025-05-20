package com.example.demo.configs;

import com.example.demo.services.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManger{

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final CustomUserDetailService customUserDetailService;

    private final CustomPasswordEncoder customPasswordEncoder;

    public CustomAuthenticationManger(
            CustomAuthenticationProvider customAuthenticationProvider,
            CustomUserDetailService customUserDetailService,
            CustomPasswordEncoder customPasswordEncoder
    ){
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.customPasswordEncoder = customPasswordEncoder;
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .authenticationProvider(customAuthenticationProvider)
                .userDetailsService(customUserDetailService)
                .passwordEncoder(customPasswordEncoder.passwordEncoder());

        return authenticationManagerBuilder.build();
    }
}
