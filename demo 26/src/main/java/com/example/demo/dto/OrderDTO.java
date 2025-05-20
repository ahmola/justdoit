package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDTO {

    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Delivery Street is required")
    private String deliveryStreet;

    @NotBlank(message = "Deliver City is required")
    private String deliveryCity;

    @NotBlank(message = "Delivery State is required")
    private String deliveryState;

    @NotBlank(message = "Delivery Zip is required")
    private String deliveryZip;

//    @CreditCardNumber
    @Column(length = 19)
    private String ccNumber;

//    @Pattern(regexp = "^(0[1-9]|1[0-2]) ([\\/]) ([2-9][0-9])$",
//            message = "Must be formatted MM/YY")
    @Column(length = 5)
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private Integer ccCVV;

    @NotEmpty(message = "you need at least one taco")
    private List<String> tacos;
}
