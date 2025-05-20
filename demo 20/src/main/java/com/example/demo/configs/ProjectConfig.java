package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.logging.Logger;

@Configuration
public class ProjectConfig {

    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public UserDetailsService userDetailsService(){

        var uds = new InMemoryUserDetailsManager();

        UserDetails user = User.withUsername("chai")
                .password("1234")
                .authorities("write")
                .build();

        uds.createUser(user);

        return uds;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf().disable();

        http
                .authorizeHttpRequests().anyRequest().hasAuthority("read")
                .and()
                .exceptionHandling().accessDeniedPage("/error")
                .and()
                .formLogin()
                .defaultSuccessUrl("/hello")
                .failureUrl("/error")
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
