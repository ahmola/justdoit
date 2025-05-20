package com.example.demo.model.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@Builder
public class TransferDTO {

    private long senderId;
    private long receiverId;
    private BigDecimal amount;
}
