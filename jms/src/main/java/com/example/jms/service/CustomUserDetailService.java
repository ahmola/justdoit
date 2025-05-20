package com.example.jms.service;

import com.example.jms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        try {
            log.info("Get " + username);
            UserDetails user = userRepository.findByUsername(username).get(0);
            log.info("User : " + user.toString());
            return user;
        }catch (Exception e){
            throw new UsernameNotFoundException("Nothing Found " + e.toString());
        }
    }
}
