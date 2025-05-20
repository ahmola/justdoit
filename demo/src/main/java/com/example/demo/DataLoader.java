package com.example.demo;

import com.example.demo.model.Coffee;
import com.example.demo.repository.CoffeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DataLoader {

    private final CoffeeRepository coffeeRepository;

    public DataLoader(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void loadData(){
        if (coffeeRepository.count() == 0){
            this.coffeeRepository.saveAll(List.of(
                    new Coffee("americano"),
                    new Coffee("cafe latte"),
                    new Coffee("caramel macciyatto"),
                    new Coffee("espresso")
            ));
        }
    }
}
