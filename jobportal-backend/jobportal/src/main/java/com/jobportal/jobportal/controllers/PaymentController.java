package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.payment.CreatePaymentRequestDTO;
import com.jobportal.jobportal.services.payment.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    // dodać do global exception handlera
    @PostMapping("/create-session")
    public ResponseEntity<String> createCheckoutSession(@RequestBody CreatePaymentRequestDTO requestDTO) throws StripeException {
        try {
            return new ResponseEntity<>(paymentService.createPayment(requestDTO), HttpStatus.CREATED);
        }catch (StripeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
