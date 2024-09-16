package com.jobportal.jobportal.entities;

import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.entities.user.Company;
import com.jobportal.jobportal.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(name = "amount", precision = 7, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "company_user_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "payment_stripe_price_id", nullable = false)
    private PaymentStripePrice paymentStripePrice;
}
