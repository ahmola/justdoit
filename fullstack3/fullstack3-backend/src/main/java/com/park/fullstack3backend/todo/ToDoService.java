package com.park.fullstack3backend.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;
}
