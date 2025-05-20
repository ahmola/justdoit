package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errorController {

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
