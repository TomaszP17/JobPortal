package com.jobportal.jobportal.dtos.company;

public record CreateCompanyRequestDTO(
        String name,
        String nip,
        String email,
        String password) {
}
