package com.jobportal.jobportal.dtos.payment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreatePaymentRequestDTO(
        @NotNull(message = "Company ID is required")
        Long companyId,
        @NotNull(message = "Offer ID is required")
        Long offerId,
        @NotNull(message = "Price ID is required")
        String priceId
) {
}
