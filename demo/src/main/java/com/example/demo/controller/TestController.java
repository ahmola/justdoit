package com.example.demo.controller;

import com.example.demo.dto.CoffeeDTO;
import com.example.demo.model.Coffee;
import com.example.demo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class TestController {

    private final CoffeeRepository coffeeRepository;

    public TestController(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Coffee>> getCoffees(){
        return new ResponseEntity<>(coffeeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Coffee>> getCoffeeById(@PathVariable long id){
        Coffee coffee = coffeeRepository.findById(id).get();
        log.info("Coffee : " + coffee.toString());

        if(coffee.getName().isEmpty())
           return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(Optional.of(coffee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postCoffee(@RequestBody String name){
        log.info("Post : " + name);

        coffeeRepository.save(new Coffee(name));
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> putCoffee(@PathVariable long id, @RequestBody CoffeeDTO coffeeDTO){
        log.info("Request put : " + id + " to " + coffeeDTO);

        if(coffeeRepository.existsById(id)){
            log.info("Data Updating...");
            return new ResponseEntity<>(
                    coffeeRepository.save(new Coffee(coffeeDTO)), HttpStatus.OK);
        }else {
            log.info("Data Creating...");
            return new ResponseEntity<>(
                    coffeeRepository.save(new Coffee(coffeeDTO)), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCoffee(@PathVariable long id){
        if(coffeeRepository.existsById(id)){
            coffeeRepository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
