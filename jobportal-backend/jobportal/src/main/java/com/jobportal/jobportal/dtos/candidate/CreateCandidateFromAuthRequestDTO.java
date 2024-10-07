package com.jobportal.jobportal.dtos.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCandidateFromAuthRequestDTO(
        @NotBlank(message = "First name cannot be empty")
        @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
        @Pattern(regexp = "^[a-zA-Zà-žÀ-Ž'\\- ]+$", message = "First name contains invalid characters")
        String firstName,

        @NotBlank(message = "Last name cannot be empty")
        @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
        @Pattern(regexp = "^[a-zA-Zà-žÀ-Ž'\\- ]+$", message = "Last name contains invalid characters")
        String lastName,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email should be valid")
        String email
) {
}
