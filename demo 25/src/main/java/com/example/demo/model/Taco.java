package com.example.demo.model;

import com.example.demo.dto.IngredientDTO;
import com.example.demo.dto.TacoDTO;
import com.example.demo.repositories.IngredientRepository;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "taco_id")
    @JoinTable(
            name = "taco_ingredient",
            joinColumns = @JoinColumn(name = "taco_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    @CreationTimestamp
    private Date createdAt;

    public Taco(TacoDTO tacoDTO){
        this.name = tacoDTO.getName();

        // listing ingredient

    }
}
