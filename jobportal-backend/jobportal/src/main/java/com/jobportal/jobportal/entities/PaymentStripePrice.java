package com.jobportal.jobportal.entities;

import com.jobportal.jobportal.enums.StripeSubscriptionDuration;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment_stripe_price")
@Data
public class PaymentStripePrice {

    @Id
    @Enumerated(EnumType.STRING)
    private StripeSubscriptionDuration stripeSubscriptionDuration;

    @Column(nullable = false, unique = true)
    private String stripePriceId;

    @OneToMany(mappedBy = "paymentStripePrice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();

}
