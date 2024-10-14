package com.jobportal.jobportal.dtos.offer;

import java.util.List;

public record NextOfferResponseDTO(
    Long id,
    String title,
    Integer numberOfDays,
    Integer salaryMin,
    Integer salaryMax,
    String companyName,
    List<String> technologyNames
) {
}
