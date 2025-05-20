package com.example.demo.controllers.restful;

import com.example.demo.model.Taco;
import com.example.demo.repositories.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/tacos",
                produces = "application/json")
@CrossOrigin(origins = {"http://tacocloud:8080", "http://tacocloud.com"})
public class TacoController {
    private final TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest pageRequest = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest);
    }
}
