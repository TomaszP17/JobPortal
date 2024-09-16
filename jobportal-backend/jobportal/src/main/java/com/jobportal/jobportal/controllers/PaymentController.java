package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.payment.CheckoutRequestDTO;
import com.jobportal.jobportal.dtos.payment.CreatePaymentRequestDTO;
import com.jobportal.jobportal.services.payment.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/create-session")
    public Map<String, Object> createCheckoutSession(@RequestBody CreatePaymentRequestDTO requestDTO) throws StripeException {
        return null;
    }
}
