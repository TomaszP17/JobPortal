package com.jobportal.jobportal.services.company;

import com.jobportal.jobportal.dtos.company.CreateCompanyRequestDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyResponseDTO;

public interface CompanyService {

    CreateCompanyResponseDTO createCompany(CreateCompanyRequestDTO createCompanyRequestDTO);
}
