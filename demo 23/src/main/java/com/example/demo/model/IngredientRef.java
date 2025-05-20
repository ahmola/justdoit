package com.example.demo.model;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientRef {

    private String ingredient;

    @ManyToOne
    private Taco taco;

    private Long tacoKey;
}
