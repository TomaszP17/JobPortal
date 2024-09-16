package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.PaymentStripePrice;
import com.jobportal.jobportal.enums.StripeSubscriptionDuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStripePriceRepository extends JpaRepository<PaymentStripePrice, StripeSubscriptionDuration> {

}
