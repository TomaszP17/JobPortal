package com.jobportal.jobportal.dtos.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record CreateAdminRequestDTO(
        @NotBlank(message = "Nickname cannot be empty")
        @Size(min = 3, max = 50, message = "Nickname must be between 3 and 50 characters")
        String nickname,
        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password must not be blank.")
        @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters.")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
        String password
        ) {
}
