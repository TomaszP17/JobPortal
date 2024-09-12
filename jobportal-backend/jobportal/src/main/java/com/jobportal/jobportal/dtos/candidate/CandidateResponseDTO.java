package com.jobportal.jobportal.dtos.candidate;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record CandidateResponseDTO(

        String firstName,

        String lastName,

        Integer experienceYears,

        String email,

        String githubLink,

        @URL(message = "Linkedin link should be valid")
        String linkedinLink) {
}
