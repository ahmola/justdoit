package com.example.jpa;

import com.example.jpa.model.Message;
import com.example.jpa.repository.CustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JpaApplicationTests {
	@Autowired
	CustomRepository customRepository;

	@Test
	void storeLoadMessage() {
		Message message = new Message();
		message.setText("hello world");

		try {
			customRepository.save(message);
		}finally {
			System.out.print(customRepository.findByText(message.getText()));
		}
	}

}
