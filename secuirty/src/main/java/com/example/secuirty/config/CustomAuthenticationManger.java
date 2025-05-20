package com.example.secuirty.config;

import com.example.secuirty.repository.UserRepository;
import com.example.secuirty.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationManger {

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final CustomUserDetailService customUserDetailService;
    private final CustomPasswordEncoder customPasswordEncoder;

    public CustomAuthenticationManger(
            CustomAuthenticationProvider customAuthenticationProvider,
            CustomUserDetailService customUserDetailService,
            CustomPasswordEncoder customPasswordEncoder
    ){
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.customUserDetailService = customUserDetailService;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        log.info("Building Manager");
        authenticationManagerBuilder
                        .authenticationProvider(customAuthenticationProvider)
                        .userDetailsService(customUserDetailService)
                        .passwordEncoder(customPasswordEncoder.passwordEncoder())
                        ;

        return authenticationManagerBuilder.build();
    }
}
