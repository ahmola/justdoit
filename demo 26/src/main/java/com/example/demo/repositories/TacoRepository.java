package com.example.demo.repositories;

import com.example.demo.model.Taco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacoRepository extends JpaRepository<Taco, Long> {

    List<Taco> findByName(String name);

    Page<Taco> findAll(Pageable pageable);
}
