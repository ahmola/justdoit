package com.example.demo.model;

import com.example.demo.dto.CoffeeDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Coffee {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String name;

    public Coffee(CoffeeDTO coffeeDTO){
        this.id = coffeeDTO.getId();
        this.name = coffeeDTO.getName();
    }
    public Coffee(String name){
        this.name = name;
    }
}
