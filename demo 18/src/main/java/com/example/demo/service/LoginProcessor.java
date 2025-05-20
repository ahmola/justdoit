package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginProcessor {

    private User user = new User();

    private AccountRepository accountRepository;

    public LoginProcessor(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public void setUsername(String username){
        user.setUsername(username);
    }

    public void setPassword(String password){
        user.setPassword(password);
    }

    public boolean login(){

        if(user != null)
            if(!accountRepository.findByUsername(user.getUsername()).isEmpty())
                return true;

        return false;
    }
}
