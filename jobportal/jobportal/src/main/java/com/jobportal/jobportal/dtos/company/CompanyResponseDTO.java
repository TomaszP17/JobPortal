package com.jobportal.jobportal.dtos.company;

public record CompanyResponseDTO(
        String name,
        String nip,
        String email,
        String linkedinLink
) {
}
