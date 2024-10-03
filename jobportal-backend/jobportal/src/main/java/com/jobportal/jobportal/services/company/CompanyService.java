package com.jobportal.jobportal.services.company;

import com.jobportal.jobportal.dtos.company.CompanyResponseDTO;
import com.jobportal.jobportal.dtos.company.CompanyResponseOfferStatsDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyRequestDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyResponseDTO;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.Company;

import java.util.List;

public interface CompanyService {
    CreateCompanyResponseDTO createCompany(CreateCompanyRequestDTO createCompanyRequestDTO);
    CompanyResponseDTO getCompanyById(Long id);
    List<CompanyResponseDTO> getAllCompanies();
    List<CompanyResponseOfferStatsDTO> getCompaniesWithOfferStats(String sortBy, int page, int size);
    Company createCompanyFromOAuth(String email);
    void updateCompany(Long id, CreateCompanyRequestDTO createCompanyRequestDTO);
}
