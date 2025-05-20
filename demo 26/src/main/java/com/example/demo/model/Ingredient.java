package com.example.demo.model;

import com.example.demo.dto.IngredientDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String abbreviation;

    @Enumerated(EnumType.STRING)
    private Type type;

//    @ManyToMany(mappedBy = "ingredients")
//    @JsonIgnore
//    private List<Taco> tacos = new ArrayList<>();

    public Ingredient(IngredientDTO ingredientDTO){
        this.name = ingredientDTO.getName();
        this.abbreviation = ingredientDTO.getAbbreviation();
        this.type = Type.valueOf(ingredientDTO.getType());
    }
}
