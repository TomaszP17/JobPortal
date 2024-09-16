package com.jobportal.jobportal.services.payment;

import com.jobportal.jobportal.dtos.payment.CreatePaymentRequestDTO;
import com.stripe.exception.StripeException;

public interface PaymentService {
    String createPayment(CreatePaymentRequestDTO createPaymentRequestDTO) throws StripeException;
}
