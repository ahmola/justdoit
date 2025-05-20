package com.example.secuirty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationManger customAuthenticationManger;

    public SecurityConfig(
            CustomAuthenticationManger customAuthenticationManger
    ){
        this.customAuthenticationManger = customAuthenticationManger;
    }

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth ->{
//                    auth.anyRequest().authenticated();
//                })
//                .formLogin()
//                .and()
//                .authenticationManager(customAuthenticationManger.authenticationManager(http))
//                .build();
//    }
}
