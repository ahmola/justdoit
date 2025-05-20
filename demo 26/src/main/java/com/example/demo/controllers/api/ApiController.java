package com.example.demo.controllers.api;

import com.example.demo.dto.TacoDTO;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api",
produces = {"application/json", "text/xml"})
@CrossOrigin(origins = {"http://localhost:8080"})
public class ApiController {

    private final TacoRepository tacoRepository;
    private final IngredientRepository ingredientRepository;

    public ApiController(
            TacoRepository tacoRepository,
            IngredientRepository ingredientRepository){
        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping(params = "recent")
    public List<Taco> findAll(){
        PageRequest page = PageRequest.of(
                0, 3, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Taco>> tacoById(@PathVariable("id") Long id){
        Optional<Taco> taco = tacoRepository.findById(id);

        if(taco.isPresent()){
            return ResponseEntity.ok(taco);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Taco> postTaco(@RequestBody TacoDTO tacoDTO){
        log.info("Get " + tacoDTO.toString());

        Taco taco = new Taco();
        taco.setName(tacoDTO.getName());
        for(String ingredient: tacoDTO.getIngredients()){
            List<Ingredient> newIngredient = ingredientRepository.findByName(ingredient);

            if(newIngredient.isEmpty())
                return (ResponseEntity<Taco>) ResponseEntity.notFound();
            else{
                taco.getIngredients().add(newIngredient.get(0).getName());
            }
        }

        log.info("Create " + taco.toString());
        tacoRepository.save(taco);

        return ResponseEntity.ok(tacoRepository.findByName(taco.getName()).get(0));
    }

    @GetMapping("/ingredients")
    public Iterable<Ingredient> allIngredients(){
        return ingredientRepository.findAll();
    }

    @PostMapping("/ingredients")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    @DeleteMapping("/ingredients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") Long ingredientId){
        ingredientRepository.deleteById(ingredientId);
    }
}
