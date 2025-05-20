package com.example.demo.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@Builder
public class User {
    private String username;

    private String password;
}
