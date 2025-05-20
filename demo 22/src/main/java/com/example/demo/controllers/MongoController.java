package com.example.demo.controllers;

import com.example.demo.model.Ingredient;
import com.example.demo.model.TacoOrder;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoController {

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/")
    public String saveInMongo(@RequestBody String order,
                              String name){
        ingredientRepository.save(new Ingredient(name));
        orderRepository.save(new TacoOrder(order));
        return "yeah!";
    }

}
