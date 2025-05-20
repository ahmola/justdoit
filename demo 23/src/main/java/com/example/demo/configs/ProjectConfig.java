package com.example.demo.configs;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;
import com.example.demo.model.Type;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.TacoRepository;
import com.example.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class ProjectConfig {

    private final UserRepository userRepository;
    private final TacoRepository tacoRepository;
    private final IngredientRepository ingredientRepository;

    public ProjectConfig(
            UserRepository userRepository,
            TacoRepository tacoRepository,
            IngredientRepository ingredientRepository){
        this.userRepository = userRepository;
        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Bean
    public CommandLineRunner dataLoader(){
        return args -> {
            List<Ingredient> ingredients = Arrays.asList(
            new Ingredient(
                    "FLTO", "Flour Tortilla", Type.WRAP),
            new Ingredient(
                    "COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient(
                    "GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient(
                    "CARN", "Carnitas", Type.PROTEIN),
            new Ingredient(
                    "TMTO", "DIced Tomatoes", Type. VEGGIES),
            new Ingredient(
                    "LETC", "Lettuce", Type.VEGGIES),
            new Ingredient(
                    "CHED", "Cheddar", Type.CHEESE),
            new Ingredient(
                    "SLSA", "Salsa", Type.SAUCE),
            new Ingredient(
                    "SRCR", "Sour Cream", Type.SAUCE)
            );
            for (Ingredient i: ingredients
                 ) {
                if (ingredientRepository.existsById(i.getId())){
                    ingredientRepository.save(i);
                    log.info("save " + i);
                }
            }

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    ingredientRepository.findById("FLTO").get(),
                    ingredientRepository.findById("GRBF").get(),
                    ingredientRepository.findById("CARN").get(),
                    ingredientRepository.findById("CHED").get(),
                    ingredientRepository.findById("JACK").get(),
                    ingredientRepository.findById("SRCR").get()
            ));
            if(tacoRepository.findById(taco1.getId()).isEmpty()) {
                tacoRepository.save(taco1);
                log.info("save " + taco1);
            }

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    ingredientRepository.findById("COTO").get(),
                    ingredientRepository.findById("GRBF").get(),
                    ingredientRepository.findById("CHED").get(),
                    ingredientRepository.findById("JACK").get(),
                    ingredientRepository.findById("SRCR").get()
            ));
            if(tacoRepository.findById(taco2.getId()).isEmpty()) {
                tacoRepository.save(taco2);
                log.info("save " + taco2);
            }

        };
    }
}
