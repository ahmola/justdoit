package com.example.jpa.repository;

import com.example.jpa.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomRepository extends CrudRepository<Message, Long> {

    List<Message> findByText(String text);
}
