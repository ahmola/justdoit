package com.example.demo;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DemoApplicationTests {

	@MockBean
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	@Test
	void TransferServiceTransferAmountTest() throws Exception {
		Account sender = new Account();
		sender.setId(1);
		sender.setAmount(new BigDecimal(1000));

		Account receiver = new Account();
		receiver.setId(2);
		receiver.setAmount(new BigDecimal(1000));

		when(accountRepository.findById(1L))
				.thenReturn(Optional.of(sender));
		when(accountRepository.findById(2L))
				.thenReturn(Optional.of(receiver));

		accountService.transferMoney(1,2,new BigDecimal(100));

		verify(accountRepository).changeAmount(1, new BigDecimal(900));
		verify(accountRepository).changeAmount(2, new BigDecimal(1100));

	}

}
