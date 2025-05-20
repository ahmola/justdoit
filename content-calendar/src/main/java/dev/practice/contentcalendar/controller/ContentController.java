package dev.practice.contentcalendar.controller;

import dev.practice.contentcalendar.model.Content;
import dev.practice.contentcalendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    private final ContentCollectionRepository repository;

    public ContentController(ContentCollectionRepository contentCollectionRepository){
        this.repository = contentCollectionRepository;
    }

    // make a  request and find all the pieces of content in the system
    @GetMapping
    public List<Content> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable(name = "id") Integer id){
        return repository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found!"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Content content){
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable(name = "id") Integer id){
        if(!repository.existById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found!");
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id){
        repository.delete(id);
    }
}
