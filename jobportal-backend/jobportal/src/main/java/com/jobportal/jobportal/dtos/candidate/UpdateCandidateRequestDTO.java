package com.jobportal.jobportal.dtos.candidate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record UpdateCandidateRequestDTO(
        @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
        @Pattern(regexp = "^[a-zA-Zà-žÀ-Ž'\\- ]+$", message = "First name contains invalid characters")
        String firstName,

        @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
        @Pattern(regexp = "^[a-zA-Zà-žÀ-Ž'\\- ]+$", message = "Last name contains invalid characters")
        String lastName,

        @Min(value = 0, message = "Experience years cannot be less than 0")
        @Max(value = 50, message = "Experience years cannot be more than 50")
        Integer experienceYears,

        @URL
        String githubLink,

        @URL
        String linkedinLink

) {
}
