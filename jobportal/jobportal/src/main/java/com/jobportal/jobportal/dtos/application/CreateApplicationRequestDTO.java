package com.jobportal.jobportal.dtos.application;

import jakarta.validation.constraints.NotBlank;

public record CreateApplicationRequestDTO(

        Long candidateId,
        @NotBlank(message = "Offer cannot be empty")
        Long offerId,
        byte[] pdf) {
}
