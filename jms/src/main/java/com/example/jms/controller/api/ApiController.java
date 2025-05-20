package com.example.jms.controller.api;

import com.example.jms.dto.UserDTO;
import com.example.jms.model.User;
import com.example.jms.service.ApiService;
import com.example.jms.service.jms.JmsMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    private ApiService apiService;
    private JmsMessageSender jmsMessageSender;

    public ApiController(
            ApiService apiService,
            JmsMessageSender jmsMessageSender
    ){
        this.apiService = apiService;
        this.jmsMessageSender = jmsMessageSender;
    }

    @GetMapping
    public List<User> findAll(){
        return apiService.findAllUser();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDTO userDTO) throws Exception {
        log.info("Post Request : " + userDTO.toString());
        jmsMessageSender.sendUserDTO("jmsDestination", userDTO);
        return apiService.createUser(userDTO);
    }
}
