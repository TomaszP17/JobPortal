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
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        @DecimalMin(value = "0.01", message = "Amount must be at least 0.1")
        BigDecimal amount
) {
}
