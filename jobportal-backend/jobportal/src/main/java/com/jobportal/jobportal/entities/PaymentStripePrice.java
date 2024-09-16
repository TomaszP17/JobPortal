package com.jobportal.jobportal.entities;

import com.jobportal.jobportal.entities.offer.OfferEmploymentType;
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
    @Column(length = 15)
    private StripeSubscriptionDuration stripeSubscriptionDuration;

    @Column(nullable = false, unique = true)
    private String stripePriceId;

    @OneToMany(mappedBy = "payment_stripe_price", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();

}
