package com.example.demo.configs;

import com.example.demo.services.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailService customUserDetailService;

    public CustomAuthenticationProvider(
            CustomUserDetailService customUserDetailService,
            PasswordEncoder passwordEncoder
    ){
        this.customUserDetailService = customUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        log.info(authentication.toString() + " is trying to access");

        UserDetails userDetails = customUserDetailService.loadUserByUsername(name);
        if(passwordEncoder.matches(password, userDetails.getPassword())){
            log.info("Authenticated " + userDetails.toString());

            log.info("Success Authentication");
            return new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()
            );
        }else {
            log.info("Can't find " + name);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
