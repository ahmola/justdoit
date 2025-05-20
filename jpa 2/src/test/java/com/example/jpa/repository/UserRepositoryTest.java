package com.example.jpa.repository;

import com.example.jpa.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    private List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(
                List.of(
                        new User("john", "john@rdrmail.com"),
                        new User("jack", "jack@rdrmail.com"),
                        new User("abigail", "abigail@rdrmail.com"),
                        new User("arthur", "arthur@rdrmail.com"),
                        new User("dutch", "dutch@rdrmail.com")
                )
        );

        users.get(0).setLevel(2);
        users.get(3).setLevel(2);
        users.get(4).setLevel(3);

        return users;
    }

    @BeforeAll
    void beforeAll(){
        userRepository.saveAll(generateUsers());
    }

    @AfterAll
    void afterAll(){
        userRepository.deleteAll();
    }
}