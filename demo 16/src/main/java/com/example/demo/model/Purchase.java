package com.example.demo.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@Builder
public class Purchase {

    private int id;

    private String product;

    private BigDecimal price;
}
