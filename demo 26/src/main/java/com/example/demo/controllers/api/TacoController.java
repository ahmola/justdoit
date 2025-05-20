package com.example.demo.controllers.api;

import com.example.demo.dto.TacoDTO;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.WrongClassException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/taco")
public class TacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    public TacoController(
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @PostMapping
    public ResponseEntity<String> postTaco(@RequestBody List<TacoDTO> tacoDTOS) throws Exception{
        int check = 0;
        log.info("Get " + tacoDTOS.toString());

        for(TacoDTO tacoDTO : tacoDTOS) {
            Taco taco = new Taco();

            if (!tacoRepository.findByName(tacoDTO.getName()).isEmpty())
                throw new InstanceAlreadyExistsException("Already Created!");
            taco.setName(tacoDTO.getName());

            for (String ingredientName : tacoDTO.getIngredients()) {
                List<Ingredient> ingredient = ingredientRepository.findByName(ingredientName);
                log.info("Ingredient : " + ingredient.toString());

                if (ingredient.isEmpty())
                    throw new ClassNotFoundException("No Such Ingredient Found");
                else {
                    taco.getIngredients().add(ingredient.get(0).getName());
                }
            }

            log.info("Taco Created : " + taco.toString());
            tacoRepository.save(taco);
            check++;
        }

        return ResponseEntity.ok("Success " + check);
    }

    @GetMapping
    public ResponseEntity<TacoDTO> getTaco(@RequestParam String name) throws Exception{
        log.info("Get " + name);

        List<Taco> taco = tacoRepository.findByName(name);
        if(taco.isEmpty())
            throw new InstanceNotFoundException();

        TacoDTO tacoDTO = new TacoDTO();
        tacoDTO.setName(taco.get(0).getName());
        for(String ingredient : taco.get(0).getIngredients()){
            tacoDTO.getIngredients().add(ingredient);
        }

        return ResponseEntity.ok(tacoDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TacoDTO>> getAllTaco() throws Exception{
        log.info("Get All Taco");

        List<Taco> tacos = tacoRepository.findAll();
        log.info("Tacos " + tacos.toString());
        if(tacos.isEmpty())
            throw new InstanceNotFoundException();

        List<TacoDTO> tacoDTOS = new ArrayList<>();
        for(Taco taco : tacos){
            log.info("Taco : " + taco);
            TacoDTO tacoDTO = new TacoDTO();
            tacoDTO.setName(taco.getName());
            for(String ingredient: taco.getIngredients()){
                tacoDTO.getIngredients().add(ingredient);
            }
            tacoDTOS.add(tacoDTO);
        }

        return ResponseEntity.ok(tacoDTOS);
    }
}
