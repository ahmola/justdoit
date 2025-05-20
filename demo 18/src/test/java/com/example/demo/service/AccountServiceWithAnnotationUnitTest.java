package com.example.demo.service;

import com.example.demo.config.Exception.AccountNotFoundException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@DisplayName("Test if there is no account in Repository")
@ExtendWith(MockitoExtension.class)
class AccountServiceWithAnnotationUnitTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Test
    void transferMoney() {

        Account sender = new Account();
        sender.setId(1L);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));
        given(accountRepository.findById(2L))
                .willReturn(Optional.empty());

        assertThrows(
                AccountNotFoundException.class,
                () -> accountService.transferMoney(1,2, new BigDecimal(100))
        );

        verify(accountRepository, never())
                .changeAmount(anyLong(), any());
    }
}