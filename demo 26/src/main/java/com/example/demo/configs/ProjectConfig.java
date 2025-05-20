package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.oauth2.
import java.util.Base64;

@Configuration
@EnableWebSecurity
public class ProjectConfig {

    private final CustomAuthenticationManger customAuthenticationManger;

    public ProjectConfig(
            CustomAuthenticationManger customAuthenticationManger
    ){
        this.customAuthenticationManger = customAuthenticationManger;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.httpBasic();

        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/ingredients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                        .requestMatchers(HttpMethod.DELETE, "/api/ingredients/**").hasAuthority("SCOPE_deleteIngredients")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        ;

        http
                .formLogin()
                .failureUrl("/failed")
                .defaultSuccessUrl("/api?recent")
        ;

        http.authenticationManager(customAuthenticationManger.authenticationManager(http));

        return http.build();
    }

}
