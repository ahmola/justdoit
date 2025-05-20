package com.example.demo.controller;

import com.example.demo.repository.AccountRepository;
import com.example.demo.service.LoginProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoginControllerUnitTest {

    @Mock
    private Model model;

    @Mock
    private LoginProcessor loginProcessor;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private LoginController loginController;

    @DisplayName("Test when login succeed")
    @Test
    void loginPostSuccessTest() {

        given(loginProcessor.login())
                .willReturn(true);

        String result =
                loginController.loginPost("Korsica", "password", model);

        assertEquals("login.html", result);

        verify(model)
                .addAttribute("message", "You are now logged in.");
    }

    @DisplayName("Test when login failed")
    @Test
    void loginPostFailTest(){
        given(loginProcessor.login())
                .willReturn(false);

        String result =
                loginController.loginPost("Chai", "808", model);

        assertEquals("login.html", result);

        verify(model)
                .addAttribute("message", "Login Failed!");
    }
}