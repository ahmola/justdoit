package com.example.jms.service;

import com.example.jms.dto.UserDTO;
import com.example.jms.model.User;
import com.example.jms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ApiService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUser(){
        log.info("Finding all users...");
        return userRepository.findAllUser();
    }

    public User createUser(UserDTO userDTO) throws Exception {
        log.info("Create User " + userDTO.toString());

        try {
            User user = userRepository.save(new User(userDTO));
            if(user.isEnabled())
                return user;
        }catch (Exception e){
            throw new Exception("Something's wrong " + e.toString());
        }

        return null;
    }
}
