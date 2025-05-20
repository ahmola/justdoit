package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class UserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        String userByUsernameQuery =
                "select username, password, enabled from user where username =?";
        String authsByUserQuery =
                "select username, authority from spring.authorities where username =?";
        String createUser =
                "insert into user (id, username, password, enabled) values(?,?,?,?)";

        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(userByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("scrypt", new SCryptPasswordEncoder(16384, 8, 1, 32, 64));

        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

}