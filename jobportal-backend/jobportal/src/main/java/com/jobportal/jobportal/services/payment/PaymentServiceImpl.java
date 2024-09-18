package com.jobportal.jobportal.services.payment;

import com.jobportal.jobportal.dtos.payment.CreatePaymentRequestDTO;
import com.jobportal.jobportal.entities.Payment;
import com.jobportal.jobportal.entities.PaymentStripePrice;
import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.entities.user.Company;
import com.jobportal.jobportal.enums.PaymentStatus;
import com.jobportal.jobportal.enums.StripeSubscriptionDuration;
import com.jobportal.jobportal.exceptions.offer.OfferDoesNotExistsException;
import com.jobportal.jobportal.exceptions.payment.PaymentStripePriceDoesNotExistException;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.mappers.PaymentMapper;
import com.jobportal.jobportal.repositories.CompanyRepository;
import com.jobportal.jobportal.repositories.OfferRepository;
import com.jobportal.jobportal.repositories.PaymentRepository;
import com.jobportal.jobportal.repositories.PaymentStripePriceRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final OfferRepository offerRepository;
    private final CompanyRepository companyRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final PaymentStripePriceRepository paymentStripePriceRepository;

    @Value("${stripe.domain.success}")
    private String domainSuccess;

    @Value("${stripe.domain.cancel}")
    private String domainCancel;

    public PaymentServiceImpl(OfferRepository offerRepository, CompanyRepository companyRepository,
                              PaymentMapper paymentMapper, PaymentRepository paymentRepository, PaymentStripePriceRepository paymentStripePriceRepository) {
        this.offerRepository = offerRepository;
        this.companyRepository = companyRepository;
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
        this.paymentStripePriceRepository = paymentStripePriceRepository;
    }

    @Override
    public String createPayment(CreatePaymentRequestDTO createPaymentRequestDTO) throws StripeException {

        System.out.println(createPaymentRequestDTO.stripeSubscriptionDuration());

        Company company = companyRepository
                .findById(createPaymentRequestDTO.companyId())
                .orElseThrow(() -> new UserDoesNotExistException("User with id: " + createPaymentRequestDTO.companyId() + " does not exist."));

        Offer offer = offerRepository
                .findById(createPaymentRequestDTO.offerId())
                .orElseThrow(() -> new OfferDoesNotExistsException("Offer with id: " + createPaymentRequestDTO.offerId() + " does not exist."));

        PaymentStripePrice paymentStripePrice = paymentStripePriceRepository
                .findById(createPaymentRequestDTO.stripeSubscriptionDuration())
                .orElseThrow(() -> new PaymentStripePriceDoesNotExistException("PaymentStripePrice with id: " + createPaymentRequestDTO.stripeSubscriptionDuration() + " does not exists"));

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(domainSuccess)
                .setCancelUrl(domainCancel)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPrice(paymentStripePrice.getStripePriceId())
                                .build())
                .build();

        Session session = Session.create(params);

        // mozliwe ze amount mozna pobierac inaczej
        Payment payment = Payment.builder()
                .sessionId(session.getId())
                .status(PaymentStatus.PENDING)
                .amount(Price.retrieve(paymentStripePrice.getStripePriceId()).getUnitAmountDecimal())
                .paymentStripePrice(paymentStripePrice)
                .company(company)
                .offer(offer)
                .build();
        paymentRepository.save(payment);

        return session.getUrl();
    }
}

