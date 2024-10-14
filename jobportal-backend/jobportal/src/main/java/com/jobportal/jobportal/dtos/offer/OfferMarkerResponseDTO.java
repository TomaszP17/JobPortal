package com.jobportal.jobportal.dtos.offer;

public record OfferMarkerResponseDTO(
        String title,
        Integer salaryMin,
        Integer salaryMax
) {
}
