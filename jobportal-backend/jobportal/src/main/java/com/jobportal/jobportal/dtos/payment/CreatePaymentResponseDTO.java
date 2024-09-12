package com.jobportal.jobportal.dtos.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreatePaymentResponseDTO(
        Long id,
        Long offerId,
        Long companyId,
        BigDecimal amount,
        LocalDateTime purchaseDate
) {
}
