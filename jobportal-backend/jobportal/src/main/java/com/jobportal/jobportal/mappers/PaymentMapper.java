package com.jobportal.jobportal.mappers;

import com.jobportal.jobportal.dtos.payment.CreatePaymentResponseDTO;
import com.jobportal.jobportal.entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment paymentFromCreateRequest(CreatePaymentResponseDTO createPaymentResponseDTO);
    CreatePaymentResponseDTO createResponseFromPayment(Payment payment);
}
