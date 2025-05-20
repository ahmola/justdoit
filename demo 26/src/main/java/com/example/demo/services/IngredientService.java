package com.example.demo.services;

import com.example.demo.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class IngredientService {
    private final RestTemplate restTemplate;

    public IngredientService(
            RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Ingredient> getIngredientById(Long ingredientId){
        Map<String, Long> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);

        ResponseEntity<Ingredient> ingredient = restTemplate.getForEntity(
                "http://localhost:8080/data-api/ingredients/{id}",
                Ingredient.class, urlVariables
        );
        ingredient.getBody().setId(ingredientId);
        log.info(ingredient.toString());

        return ingredient;
    }

    public ResponseEntity<Ingredient> createIngredient(Ingredient ingredient){
        return restTemplate.postForEntity(
                "http://localhost:8080/data-api/ingredients",
                ingredient, Ingredient.class
        );
    }

    public URI createIngredientURI(Ingredient ingredient){
        return restTemplate.postForLocation("http://localhost:8080/data-api/ingredients",
                ingredient);
    }

    public void updateIngredient(Ingredient ingredient){
        log.info("Update " + ingredient.toString());
        restTemplate.put("http://localhost:8080/data-api/ingredients/{id}",
                ingredient,
                ingredient.getId());
    }

    public void deleteIngredient(Long ingredientId){
        restTemplate.delete("http://localhost:8080/data-api/ingredients/{id}",
                ingredientId);
    }
}
