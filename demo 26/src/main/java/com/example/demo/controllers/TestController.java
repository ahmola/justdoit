package com.example.demo.controllers;

import com.example.demo.dto.IngredientDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Ingredient;
import com.example.demo.model.User;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.TacoRepository;
import com.example.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TestController {

    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    private final PasswordEncoder passwordEncoder;

    public TestController(
            UserRepository userRepository,
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository,
            PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user")
    public ResponseEntity<User> postUser(@RequestBody UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        log.info("Get " + userDTO.toString());
        User user = new User(userDTO);

        userRepository.save(user);

        return ResponseEntity.ok(userRepository.findByUsername(userDTO.getUsername()).get(0));
    }

    @PostMapping("/u")
    public ResponseEntity<User> simplePostUser(@RequestParam String username){
        log.info("Get " + username);
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");

        userRepository.save(user);

        return ResponseEntity.ok(userRepository.findByUsername(user.getUsername()).get(0));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> showAllUser(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/user")
    public ResponseEntity<User> findUser(@RequestParam String username){
        return ResponseEntity.ok(userRepository.findByUsername(username).get(0));
    }
}
