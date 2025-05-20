package com.example.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private LocalDate registrationDate;
    private String email;
    private int level;
    private boolean active;

    public User(String username, String email){
        this.username = username;
        this.registrationDate = LocalDate.now();
        this.level = 1;
        this.email = email;
        this.active = true;
    }
}
