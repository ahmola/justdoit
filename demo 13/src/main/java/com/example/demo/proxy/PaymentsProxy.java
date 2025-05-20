package com.example.demo.proxy;

import com.example.demo.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@Component
public class PaymentsProxy {

    private final WebClient webClient;

    @Value("${name.service.url}")
    private String paymentsServiceUrl;

    Logger logger = Logger.getLogger(PaymentsProxy.class.getName());

    public PaymentsProxy(WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<Payment> createPayment(
            String requestId,
            Payment payment){

        logger.info("Get " + requestId + " amount is " + payment.getAmount());
        return webClient.post()
                .uri(paymentsServiceUrl + "/payment")
                .header("requestId", requestId)
                .body(Mono.just(payment), Payment.class)
                .retrieve()
                .bodyToMono(Payment.class)
                ;
    }
}
