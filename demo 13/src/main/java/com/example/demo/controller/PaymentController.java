package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.proxy.PaymentsProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class PaymentController {

    private final PaymentsProxy paymentsProxy;

    public PaymentController(PaymentsProxy paymentsProxy){
        this.paymentsProxy = paymentsProxy;
    }

    Logger logger = Logger.getLogger(PaymentController.class.getName());

    @PostMapping("/payment")
    public Mono<Payment> createPayment(
            @RequestBody Payment payment
    ){
        logger.info("Get " + payment.getAmount());

        String requestId = UUID.randomUUID().toString();
        return paymentsProxy.createPayment(requestId, payment);
    }
}
