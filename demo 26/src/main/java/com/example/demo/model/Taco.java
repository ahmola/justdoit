package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@RestResource(rel = "tacos", path = "tacos")
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String name;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "taco_ingredients",
//            joinColumns = @JoinColumn(name = "taco_id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
//    )
//    @JsonIgnore
//    private List<Ingredient> ingredients = new ArrayList<>();

    private List<String> ingredients = new ArrayList<>();

    @CreationTimestamp
    private Date createdAt;
}
