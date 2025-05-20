package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long tacoOrder;

    private List<TacoOrder> tacoOrders = new ArrayList<>();

    private List<Ingredient> ingredients = new ArrayList<>();

    private Long tacoOrderKey;

    private Date createdAt;
}
