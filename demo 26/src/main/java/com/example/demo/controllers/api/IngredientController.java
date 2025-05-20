package com.example.demo.controllers.api;

import com.example.demo.dto.IngredientDTO;
import com.example.demo.model.Ingredient;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.services.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final IngredientService ingredientService;

    public IngredientController(
            IngredientRepository ingredientRepository,
            IngredientService ingredientService
    ){
        this.ingredientRepository = ingredientRepository;
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<String> addIngredient(@RequestBody List<IngredientDTO> ingredientDTOS){
        int check = 0;
        log.info("Get " + ingredientDTOS.toString());

        for(IngredientDTO ingredientDTO : ingredientDTOS) {
            Ingredient ingredient = new Ingredient(ingredientDTO);
            if(ingredientRepository.findByName(ingredient.getName()).isEmpty()){
                ingredientRepository.save(ingredient);
                check++;
            }else{
                log.info("Already Exist! " + ingredient.toString());
            }
        }

        return ResponseEntity.ok("Success " + check);
    }

    @GetMapping
    public ResponseEntity<Ingredient> getIngredient(@RequestParam String name) throws Exception{
        log.info("Get " + name);

        List<Ingredient> ingredient = ingredientRepository.findByName(name);
        log.info("Required Ingredient " + ingredient.toString());

        if(ingredient.isEmpty())
            throw new ChangeSetPersister.NotFoundException();
        else
            return ResponseEntity.ok(ingredient.get(0));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findWithId(@PathVariable("id") Long id){
        return ingredientService.getIngredientById(id);
    }

    @PostMapping("/one")
    public ResponseEntity<Ingredient> createOne(@RequestBody IngredientDTO ingredientDTO){
        Ingredient ingredient = new Ingredient(ingredientDTO);
        log.info("Create " + ingredient.toString());
        return ingredientService.createIngredient(ingredient);
    }

    @PostMapping("/uri")
    public URI createURI(@RequestBody IngredientDTO ingredientDTO){
        Ingredient ingredient = new Ingredient(ingredientDTO);
        log.info("Create " + ingredient.toString());
        return ingredientService.createIngredientURI(ingredient);
    }

    @PutMapping("/{id}")
    public void updateWithId(@PathVariable("id") Long id,
                             @RequestBody IngredientDTO ingredientDTO){
        Ingredient ingredient = new Ingredient(ingredientDTO);
        ingredient.setId(id);
        log.info("Put " + ingredient);

        ingredientService.updateIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteWithId(@PathVariable("id") Long id){
        log.info("Delete " + id);
        ingredientService.deleteIngredient(id);
    }
}
