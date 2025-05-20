package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TransferService {

    Logger logger = Logger.getLogger(TransferService.class.getName());

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(
            long SenderId,
            long ReceiverId,
            BigDecimal amount
    ) throws Exception {

        Account sender = accountRepository.findById(SenderId).orElseThrow(()->
                new AccountNotFoundException());
        Account receiver = accountRepository.findById(ReceiverId).orElseThrow(()->
                new AccountNotFoundException());

        logger.info("sender: " + sender.toString() + " receiver: " + receiver);

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        logger.info("changed amount sender and receiver: " + senderNewAmount + " " + receiverNewAmount);

        accountRepository.changeAmount(SenderId, senderNewAmount);
        accountRepository.changeAmount(ReceiverId, receiverNewAmount);
    }

    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public List<Account> findAccountByName(String name){
        return accountRepository.findAccountsByName(name);
    }
}
