package com.jobportal.jobportal.services.company;

import com.jobportal.jobportal.dtos.company.*;

import java.util.List;

public interface CompanyService {
    CreateCompanyResponseDTO createCompany(CreateCompanyRequestDTO createCompanyRequestDTO);
    CompanyResponseDTO getCompanyById(Long id);
    List<CompanyResponseDTO> getAllCompanies();
    List<CompanyResponseOfferStatsDTO> getCompaniesWithOfferStats(String sortBy, int page, int size);
    void updateCompany(Long id, CreateCompanyRequestDTO createCompanyRequestDTO);
    void createCompanyFromOAuth(CreateCompanyFromOAuthRequestDTO requestDTO);
    CurrentUserCompanyDTO getCompanyByEmail(String email);
}
