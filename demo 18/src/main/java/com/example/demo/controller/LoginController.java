package com.example.demo.controller;

import com.example.demo.service.LoginProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class LoginController {

    Logger logger = Logger.getLogger(LoginController.class.getName());

    private final LoginProcessor loginProcessor;

    public LoginController(LoginProcessor loginProcessor){
        this.loginProcessor = loginProcessor;
    }

    @PostMapping("/")
    public String loginPost(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ){
        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);
        logger.info("Post from " + username);

        boolean loggedIn = loginProcessor.login();

        if(loggedIn){
            logger.info("Login Success");
            model.addAttribute("message", "You are now logged in.");
        }else {
            logger.info("Login Failed");
            model.addAttribute("message", "Login Failed!");
        }

        return "login.html";
    }
}
