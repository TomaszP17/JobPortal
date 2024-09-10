package com.jobportal.jobportal.dtos.worktype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkTypeCreateRequestDTO {

    @NotBlank(message = "name cannot be empty")
    @Size(max = 50, message = "name cannot be longer than 50 characters")
    private String name;

}
