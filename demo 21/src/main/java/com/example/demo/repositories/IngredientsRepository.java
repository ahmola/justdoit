package com.example.demo.repositories;

import com.example.demo.model.Ingredient;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//@EnableCassandraRepositories(basePackages = "com.example.demo.repositories")
//public interface IngredientsRepository extends CrudRepository<Ingredient, String> {
//    Iterable<Ingredient> findAll();
//
//    Optional<Ingredient> findById(String id);
//
//    Ingredient save(Ingredient ingredient);
//}
