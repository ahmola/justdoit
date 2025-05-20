package com.example.demo.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class Country {

    private String name;
    private int population;

    public static Country of(
            String name,
            int population
    ){
        Country country = new Country();
        country.setName(name);
        country.setPopulation(population);
        return country;
    }
}
