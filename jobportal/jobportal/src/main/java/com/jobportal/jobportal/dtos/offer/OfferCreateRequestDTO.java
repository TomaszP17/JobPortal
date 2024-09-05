package com.jobportal.jobportal.dtos.offer;

import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.experience.ExperienceCreateRequestDTO;
import com.jobportal.jobportal.dtos.technology.TechnologyCreateRequestDTO;
import com.jobportal.jobportal.dtos.worktype.WorkTypeCreateRequestDTO;

import lombok.Data;

import java.util.Set;

@Data
public class OfferCreateRequestDTO {

    private String title;

    private Integer salaryMin;

    private Integer salaryMax;

    private String description;

    private Set<TechnologyCreateRequestDTO> technologies;

    private Set<ExperienceCreateRequestDTO> experiences;

    private Set<EmploymentTypeCreateRequestDTO> employmentType;

    private Set<WorkTypeCreateRequestDTO> workTypes;



}
