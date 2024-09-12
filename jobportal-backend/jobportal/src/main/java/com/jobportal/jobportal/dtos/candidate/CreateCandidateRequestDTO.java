package com.jobportal.jobportal.dtos.candidate;

import jakarta.validation.constraints.*;

public record CreateCandidateRequestDTO(

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
        String email,

        @NotBlank(message = "Password must not be blank.")
        @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters.")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
        String password) {

//    @NotNull(message = "Experience years cannot be null")
//    @Min(value = 0, message = "Experience years cannot be less than 0")
//    @Max(value = 50, message = "Experience years cannot be more than 50")
}
