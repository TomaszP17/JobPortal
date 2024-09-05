package com.jobportal.jobportal.services.employmenttype;

import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeResponseDTO;

import java.util.List;

public interface EmploymentTypeService {

    List<EmploymentTypeResponseDTO> getAllEmploymentTypes();

    EmploymentTypeResponseDTO getEmploymentType(long employmentTypeId);

    void addEmploymentType(EmploymentTypeCreateRequestDTO requestDTO);
}
