package com.jobportal.jobportal.dtos.offer;

import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.experience.ExperienceCreateRequestDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationCreateRequestDTO;
import com.jobportal.jobportal.dtos.technology.TechnologyCreateRequestDTO;
import com.jobportal.jobportal.dtos.worktype.WorkTypeCreateRequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class OfferCreateRequestDTO {

    @NotBlank(message = "title cannot be empty")
    @Size(max = 50, message = "title cannot be longer than 50 characters")
    private String title;

    @Positive(message = "companyId must be positive")
    private Long companyId;

    @Positive(message = "salaryMin must be positive")
    private Integer salaryMin;

    @Positive(message = "salaryMax must be positive")
    private Integer salaryMax;

    @NotBlank(message = "description cannot be empty")
    private String description;

    private Set<Long> technologies;

    private Set<Long> experiences;

    private Set<Long> employmentType;

    private Set<Long> workTypes;

    private LocalizationCreateRequestDTO requestDTO;
}
