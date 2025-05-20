package com.example.demo.configs;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;
import com.example.demo.model.Type;
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

    public ProjectConfig(
            UserRepository userRepository,
            TacoRepository tacoRepository){
        this.userRepository = userRepository;
        this.tacoRepository = tacoRepository;
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
            log.info("Ingredients Generated");


            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    ingredients.get(0),
                    ingredients.get(1)
            ));
            if(tacoRepository.findById(taco1.getId()).isEmpty()) {
                tacoRepository.save(taco1);
                log.info("save " + taco1);
            }

//            Taco taco2 = new Taco();
//            taco2.setName("Bovine Bounty");
//            taco2.setIngredients(Arrays.asList(
//                    ingredientRepository.findById("COTO").get(),
//                    ingredientRepository.findById("GRBF").get(),
//                    ingredientRepository.findById("CHED").get(),
//                    ingredientRepository.findById("JACK").get(),
//                    ingredientRepository.findById("SRCR").get()
//            ));
//            if(tacoRepository.findById(taco2.getId()).isEmpty()) {
//                tacoRepository.save(taco2);
//                log.info("save " + taco2);
//            }

        };
    }
}
