package com.jobportal.jobportal.services.payment;

import com.jobportal.jobportal.dtos.payment.CreatePaymentRequestDTO;
import com.jobportal.jobportal.dtos.payment.CreatePaymentResponseDTO;

public interface PaymentService {
    CreatePaymentResponseDTO createPayment(CreatePaymentRequestDTO createPaymentRequestDTO);
}
