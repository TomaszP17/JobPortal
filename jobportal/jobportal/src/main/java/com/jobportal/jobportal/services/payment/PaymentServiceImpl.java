package com.jobportal.jobportal.services.payment;

import com.jobportal.jobportal.dtos.payment.CreatePaymentRequestDTO;
import com.jobportal.jobportal.dtos.payment.CreatePaymentResponseDTO;
import com.jobportal.jobportal.mappers.PaymentMapper;
import com.jobportal.jobportal.repositories.CompanyRepository;
import com.jobportal.jobportal.repositories.OfferRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final OfferRepository offerRepository;
    private final CompanyRepository companyRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(OfferRepository offerRepository, CompanyRepository companyRepository, PaymentMapper paymentMapper) {
        this.offerRepository = offerRepository;
        this.companyRepository = companyRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public CreatePaymentResponseDTO createPayment(CreatePaymentRequestDTO createPaymentRequestDTO) {
        return null;
    }
}
