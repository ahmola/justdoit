package com.example.secuirty.model;

import com.example.secuirty.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

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
