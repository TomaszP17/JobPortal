package com.jobportal.jobportal.dtos.candidate;

public record UpdateCandidateResponseDTO(
        String firstName,
        String lastName,
        Integer experienceYears,
        String githubLink,
        String linkedinLink
) {
}
