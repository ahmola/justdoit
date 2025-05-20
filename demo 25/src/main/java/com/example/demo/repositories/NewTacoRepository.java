package com.example.demo.repositories;

import com.example.demo.model.Taco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewTacoRepository extends CrudRepository<Taco, Long> {

    Page<Taco> findAll(Pageable pageable);
}
