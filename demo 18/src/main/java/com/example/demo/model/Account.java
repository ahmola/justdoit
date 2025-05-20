package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@Builder
public class Account {

    @Id
    private long id;

    private String name;

    private BigDecimal amount;
}
