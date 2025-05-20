package com.example.jms.service.jms;

import com.example.jms.dto.UserDTO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageSender {

    private final JmsTemplate jmsTemplate;

    public JmsMessageSender(
            JmsTemplate jmsTemplate
    ){
        this.jmsTemplate = jmsTemplate;
    }

    public void sendUserDTO(String destination, UserDTO userDTO){
        jmsTemplate.convertAndSend(destination, userDTO);
    }
}
