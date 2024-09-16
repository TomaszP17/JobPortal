package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.PaymentStripePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStripePriceRepository extends JpaRepository<PaymentStripePrice, String> {

}
