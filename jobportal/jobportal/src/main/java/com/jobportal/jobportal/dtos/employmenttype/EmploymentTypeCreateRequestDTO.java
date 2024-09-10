package com.jobportal.jobportal.dtos.employmenttype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentTypeCreateRequestDTO {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 50, message = "Name cannot be longer than 50 characters")
    private String name;
}
