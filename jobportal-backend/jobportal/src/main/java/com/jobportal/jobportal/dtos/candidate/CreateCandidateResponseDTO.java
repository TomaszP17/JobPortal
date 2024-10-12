package com.jobportal.jobportal.dtos.candidate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCandidateResponseDTO(

        @NotBlank(message = "First name cannot be empty")
        @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
        @Pattern(regexp = "^[a-zA-Zà-žÀ-Ž'\\- ]+$", message = "First name contains invalid characters")
        String firstName
){
}
