package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceUnitTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @DisplayName("Test the amount is transferred " +
    "from one account to another if no exception occurs.")
    @Test
    void transferMoneyHappyFlow() throws Exception {

        Account sender = new Account();
        sender.setId(1L);
        sender.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2L);
        receiver.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));

        given(accountRepository.findById(receiver.getId()))
                .willReturn(Optional.of(receiver));

        accountService.transferMoney(
                sender.getId(),
                receiver.getId(),
                new BigDecimal(100)
        );

        verify(accountRepository)
                .changeAmount(1L, new BigDecimal(900));
        verify(accountRepository)
                .changeAmount(2L, new BigDecimal(1100));
    }
}