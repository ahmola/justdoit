package com.example.prac.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Lob
    private String text;

    @ColumnDefault("0")
    private int likes;

    @ManyToOne
    @JoinColumn(name="user")
    private user writer;

    @OneToMany
    @JoinColumn(name="image")
    private List<Image> image;

    @CreationTimestamp
    private Timestamp timestamp;
}
