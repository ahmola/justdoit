package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "greeting")
public class Greeting {

    private String name;
    private String coffee;
}
