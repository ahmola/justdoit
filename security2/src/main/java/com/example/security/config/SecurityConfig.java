package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .formLogin()
                .failureForwardUrl("/error")
                .defaultSuccessUrl("/");
        http
                .authorizeHttpRequests(
                        auth -> auth
                                .anyRequest().authenticated()
                )
                ;


        return http.build();
    }
}
