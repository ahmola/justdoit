package com.example.demo.repositories;

import com.example.demo.model.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

    @Query("select * from taco")
    Iterable<Taco> findAll(PageRequest pageRequest);
}
