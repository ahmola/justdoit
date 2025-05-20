package com.example.secuirty.service;

import com.example.secuirty.config.CustomPasswordEncoder;
import com.example.secuirty.model.User;
import com.example.secuirty.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(
            UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username).get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Start Find : "+username);
        User user = userRepository.findByUsername(username).get(0);
        if(user.equals(null)){
            log.info("Can't find " + username);
            return null;
        }else {
            log.info("Find " + user.toString());

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
            log.info("Roles: " + authorities.toString());

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    authorities
            );
        }
    }
}
