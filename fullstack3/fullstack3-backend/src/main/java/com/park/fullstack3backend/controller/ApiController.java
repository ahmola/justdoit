package com.park.fullstack3backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1")
@RestController
public class ApiController {

    @Value("${spring.application.name}")
    private String name;

    @GetMapping
    public String getRoot(){
        log.info(name);
        return "hello world";
    }

}
