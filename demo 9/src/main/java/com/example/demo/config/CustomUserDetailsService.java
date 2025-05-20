package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService uds(){
        var uds = new InMemoryUserDetailsManager();

        var u = User.withUsername("john")
                .password(passwordEncoder.encode("12345"))
                .authorities("read")
                .build();

        uds.createUser(u);

        return uds;
    }
}
