package com.example.jpa;

import com.example.jpa.model.User;
import com.example.jpa.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

//	@Bean
//	public ApplicationRunner configure(UserRepository userRepository){
//		return env -> {
//			User user1 = new User("chai", "chia@vandellaymail.com");
//			User user2 = new User("808", "808@vandellaymail.com");
//
//			userRepository.saveAll(List.of(user1, user2));
//
//			userRepository.findAll().forEach(System.out::println);
//		};
//	}
}
