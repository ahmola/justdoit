package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.dto.TransferRequest;
import com.example.demo.service.TransferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class AccountController {

    Logger logger = Logger.getLogger(AccountController.class.getName());

    private final TransferService transferService;

    public AccountController(TransferService transferService){
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest transferRequest) throws Exception {

        logger.info("Sender: " + transferRequest.getSenderAccountId()
                + " Receiver: " + transferRequest.getReceiverAccountId());

        transferService.transferMoney(
                transferRequest.getSenderAccountId(),
                transferRequest.getReceiverAccountId(),
                transferRequest.getAmount()
        );

    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
            @RequestParam(required = false) String name
            ){

        if(name != null){
            return transferService.findAccountByName(name);
        }

        return transferService.getAllAccounts();
    }
}
