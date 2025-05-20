package com.example.demo.controller;

import com.example.demo.model.Greeting;
import com.example.demo.model.Pojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ConfigController {
    private final Greeting greeting;
    private final Pojo pojo;

    public ConfigController(Greeting greeting, Pojo pojo){
        this.greeting = greeting;
        this.pojo = pojo;
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        return greeting.getCoffee();
    }

    @GetMapping("/pojo")
    public Pojo getPojo(){
        return pojo;
    }
}
