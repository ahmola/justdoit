package com.example.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class testControllers {

    @GetMapping
    public String hi(){
        log.info("hi");
        return "hi";
    }
}
