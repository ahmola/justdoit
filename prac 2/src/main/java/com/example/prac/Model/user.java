package com.example.prac.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 15)
    private String username;

    @Column(nullable = false, length = 15)
    private String password;

    @Column(nullable = false)
    private String email;

    @ColumnDefault("'User'")
    @Enumerated(EnumType.STRING)
    private roletype role;

    @ColumnDefault("0")
    private int numberOfboard;

    @CreationTimestamp
    private Timestamp timestamp;
}
