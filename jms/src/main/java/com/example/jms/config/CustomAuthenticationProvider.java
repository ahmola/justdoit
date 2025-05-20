package com.example.jms.config;

import com.example.jms.model.Role;
import com.example.jms.model.User;
import com.example.jms.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = (User) customUserDetailService.loadUserByUsername(name);

        if(customPasswordEncoder.passwordEncoder().matches(password, user.getPassword())){
            log.info("load User Success");

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
            log.info("Authorities : " + authorities.toString());

            return new UsernamePasswordAuthenticationToken(
                    name, password, authorities
            );
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
