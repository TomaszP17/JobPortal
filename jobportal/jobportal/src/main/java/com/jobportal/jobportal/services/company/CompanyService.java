package com.jobportal.jobportal.services.company;

import com.jobportal.jobportal.dtos.candidate.CandidateResponseDTO;
import com.jobportal.jobportal.dtos.company.CompanyResponseDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyRequestDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyResponseDTO;

import java.util.List;

public interface CompanyService {
    CreateCompanyResponseDTO createCompany(CreateCompanyRequestDTO createCompanyRequestDTO);
    CompanyResponseDTO getCompanyById(Long id);
    List<CompanyResponseDTO> getAllCompanies();
}
