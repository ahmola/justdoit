package com.example.demo.service;

import com.example.demo.config.exception.NotEnoughMoneyException;
import com.example.demo.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class Paymentservice {

    public PaymentDetails processPayment(){
        throw new NotEnoughMoneyException();
    }
}
