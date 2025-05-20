package com.example.demo.model;

import com.example.demo.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(UserDTO userDTO){
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        if(userDTO.getRole() == null || userDTO.getRole().isEmpty())
            this.role = Role.ROLE_USER;
        else
            this.role = Role.valueOf(userDTO.getRole());
    }
}
