package dev.practice.contentcalendar.repository;

import dev.practice.contentcalendar.model.Content;
import dev.practice.contentcalendar.model.Status;
import dev.practice.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Repository
public class ContentCollectionRepository {
    private final List<Content> contents = new ArrayList<>();

    public List<Content> findAll(){
        return contents;
    }

    public Optional<Content> findById(Integer id){
        return contents.stream()
                .filter(c -> c.id().equals(id)).findFirst();
    }

    public boolean existById(Integer id){
        return contents.stream()
                .anyMatch(c -> c.id().equals(id));
    }

    public void save(Content content){
        contents.removeIf(c -> c.id().equals(content.id()));
        contents.add(content);
    }

    @PostConstruct
    private void init(){
        Content content = new Content(1,
                "My First Blog Post",
                "Fist Post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");
        contents.add(content);
    }

    public void delete(Integer id) {
        contents.removeIf(c -> c.id().equals(id));
    }
}
