package com.jobportal.jobportal.dtos.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCompanyRequestDTO(

        @NotBlank(message = "Company name must not be blank.")
        @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters.")
        String name,

        @Pattern(regexp = "\\d{10}", message = "NIP must be a valid 10-digit number.")
        String nip,

        @NotBlank(message = "Email must not be blank.")
        @Email(message = "Email must be valid.")
        String email,

        @NotBlank(message = "Password must not be blank.")
        @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters.")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
        String password) {
}
