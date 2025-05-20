package com.example.demo.repository;

import com.example.demo.model.Account;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("SELECT * FROM account")
    List<Account> findAll();

    @Query("SELECT name FROM account WHERE name = :name")
    List<Account> findByUsername(@Param("name") String name);

    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    void changeAmount(@Param("id") long id, @Param("amount")BigDecimal amount);

}
