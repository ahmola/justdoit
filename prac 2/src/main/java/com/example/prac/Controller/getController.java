package com.example.prac.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class getController {

    @GetMapping("/")
    public String login(){
        return "HTML/index.html";
    }

    @GetMapping("/picture")
    public String userBoard(){
        return "HTML/picture.html";
    }
}
