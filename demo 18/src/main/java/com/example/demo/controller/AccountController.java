package com.example.demo.controller;

import com.example.demo.model.dto.TransferDTO;
import com.example.demo.service.AccountService;
import com.example.demo.service.LoginProcessor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AccountController {

    Logger logger = Logger.getLogger(AccountController.class.getName());

    private final AccountService accountService;

    private final LoginProcessor loginProcessor;

    public AccountController (
            AccountService accountService,
            LoginProcessor loginProcessor
            ){
        this.accountService = accountService;
        this.loginProcessor = loginProcessor;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferDTO transferDTO) throws Exception {

        logger.info("Get " + transferDTO.getAmount()
                + "From " + transferDTO.getSenderId()
                + ". Send to " + transferDTO.getReceiverId());

        accountService.transferMoney(
                transferDTO.getSenderId(),
                transferDTO.getReceiverId(),
                transferDTO.getAmount());
    }
}
