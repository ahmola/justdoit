package com.example.demo.controllers.rest;

import com.example.demo.model.Taco;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/taco",
                produces = {"application/json", "text/xml"})
@CrossOrigin(origins = {"http://tacocloud:8080", "http://tacocloud.com"})
public class TacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    public TacoController(
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository
    ){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
    public List<Taco> recent(){
        List<Taco> t = new ArrayList<>();

        for(Taco taco : tacoRepository.findAll()){
            log.info("add " + taco);
            t.add(taco);
        }

        log.info("what to send: " + t.toString());
        return t;
    }
}
