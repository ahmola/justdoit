package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    RegisteredClientRepository registeredClientRepository;

//    @GetMapping("/oauth")
//    public String oauth(@RegisteredOAuth2AuthorizedClient("oidc-client1")OAuth2AuthorizedClient authorizedClient){
//        return ;
//    }
}
