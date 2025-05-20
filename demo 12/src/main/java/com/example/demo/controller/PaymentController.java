package com.example.demo.controller;

import com.example.demo.config.exception.NotEnoughMoneyException;
import com.example.demo.model.ErrorDetails;
import com.example.demo.model.Payment;
import com.example.demo.model.PaymentDetails;
import com.example.demo.service.Paymentservice;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class PaymentController {

    private final Paymentservice paymentservice;

    public PaymentController(Paymentservice paymentservice){
        this.paymentservice = paymentservice;
    }

    private static Logger logger = Logger.getLogger(PaymentController.class.getName());

    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(
            @RequestHeader String requestId,
            @RequestBody Payment payment
    ){
        logger.info("Received request with ID " + requestId + ", Payment Amount: " + payment.getAmount());

        payment.setId(UUID.randomUUID().toString());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("requestId", requestId)
                .body(payment);
    }
}
