package com.example.demo.controllers;

import com.example.demo.configs.Exceptions.IngredientNotFoundException;
import com.example.demo.dto.IngredientDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.TacoDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;
import com.example.demo.model.User;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.NewTacoRepository;
import com.example.demo.repositories.TacoRepository;
import com.example.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TestControllers {
    @Autowired
    NewTacoRepository newTacoRepository;

    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    public TestControllers(
            UserRepository userRepository,
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository){
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String hello(){
        log.info("test success");
        return "hello";
    }

    @PostMapping
    public String userTest(@RequestBody UserDTO user){
        log.info("Get " + user.toString());

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        log.info("Create user " + newUser.toString());

        return userRepository.save(newUser).toString();
    }

    @PostMapping("/ingredient")
    public String ingredientTest(@RequestBody List<IngredientDTO> ingredients){
        int count = 0;
        log.info("Get " + ingredients.toString());

        for(IngredientDTO ingredient : ingredients) {
            Ingredient newIngredient = new Ingredient(ingredient);
            log.info("Create ingredient " + newIngredient.toString());
            if(ingredientRepository.findByName(newIngredient.getName()).isEmpty()) {
                ingredientRepository.save(newIngredient);
                count++;
            }
        }

        return "Success " + count;
    }

    @PostMapping("/taco")
    public String testTaco(@RequestBody List<TacoDTO> tacoDTOS) throws Exception{
        int count = 0;
        log.info("Get " + tacoDTOS.toString());

        for(TacoDTO tacoDTO : tacoDTOS){
            Taco newTaco = new Taco();

            newTaco.setName(tacoDTO.getName());
            log.info("set name " + newTaco.getName());

            for(String ingredientName: tacoDTO.getIngredients()) {
                Ingredient ingredient = new Ingredient();

                if(!ingredientRepository.findByName(ingredientName).isEmpty())
                    ingredient = ingredientRepository.findByName(ingredientName).get(0);
                else
                    throw new IngredientNotFoundException();

                newTaco.getIngredients().add(ingredient);
                log.info("put " + ingredient.toString() + " to " + newTaco.getName());
            }

            if(tacoRepository.findByName(newTaco.getName()).isEmpty()){
                tacoRepository.save(newTaco);
                log.info("Create Taco " + newTaco.toString());
                count++;
            }
        }

        return "Success " + count;
    }

    @PostMapping("/user")
    public String insertUser(@RequestBody UserDTO userDTO){
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        userRepository.save(user);

        return userRepository.findAll().toString();
    }
    @GetMapping("/user")
    public String user(@RequestBody String username) throws Exception{
        return userRepository.findUserByUsername(username).toString();
    }


    @GetMapping("/all")
    public Page<Taco> getAll(@RequestBody PageRequestDTO page){
        PageRequest pageable = PageRequest.of(
                page.getPage(),
                page.getSize(),
                Sort.by(
                        Sort.Direction.fromString(page.getSortDirection()),
                        page.getSortProperty())
        );

        return newTacoRepository.findAll(pageable);
    }

    @GetMapping("/ingredient")
    public ResponseEntity<String> showIngredient(@RequestBody String name){
        log.info(name);
        return ResponseEntity.ok(ingredientRepository.findByName(name).toString());
    }

    @GetMapping("/taco")
    public String showTaco(@RequestBody String tacoName){
        return tacoRepository.findByName(tacoName).toString();
    }
}