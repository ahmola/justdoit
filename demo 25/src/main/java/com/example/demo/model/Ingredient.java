package com.example.demo.model;

import com.example.demo.dto.IngredientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String abbreviation;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    /* This will cause circular error so when you need to use this,
    * you have to
    * design DTO or {fetchType.LAZY, @JsonIgnore}
    * fetchType.EAGER, In repository @Query("SELECT e FROM YourEntity e LEFT JOIN FETCH e.relatedEntities WHERE e.id = :id")
    * and etc... */
//    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
//    private List<Taco> tacos = new ArrayList<>();

    public Ingredient(IngredientDTO ingredientDTO){
        setAbbreviation(ingredientDTO.getAbbreviation());
        setName(ingredientDTO.getName());
        setType(Type.valueOf(ingredientDTO.getType()));
    }
}
