package com.example.demo.repositories;

import com.example.demo.model.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
    List<Taco> findByName(String name);
}
