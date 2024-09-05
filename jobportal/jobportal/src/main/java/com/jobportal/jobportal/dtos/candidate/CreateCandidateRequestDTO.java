package com.jobportal.jobportal.dtos.candidate;

public record CreateCandidateRequestDTO(
        String firstName,
        String lastName,
        String email,
        String password) {
}
