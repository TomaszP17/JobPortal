package com.jobportal.jobportal.dtos.candidate;

public record CandidateResponseDTO(
        String firstName,
        String lastName,
        Integer experienceYears,
        String email,
        String githubLink,
        String linkedinLink) {
}
