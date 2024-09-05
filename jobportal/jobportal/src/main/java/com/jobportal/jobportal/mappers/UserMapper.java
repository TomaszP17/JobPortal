package com.jobportal.jobportal.mappers;

import com.jobportal.jobportal.dtos.candidate.CandidateResponseDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateRequestDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateResponseDTO;
import com.jobportal.jobportal.dtos.company.CompanyResponseDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyRequestDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyResponseDTO;
import com.jobportal.jobportal.entities.user.Admin;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.Company;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    Candidate toCandidateFromCreateRequest(CreateCandidateRequestDTO candidateDTO, @Context PasswordEncoder passwordEncoder);
    CreateCandidateResponseDTO toCreateResponseFromCandidate(Candidate candidate);
    CandidateResponseDTO toResponseFromCandidate(Candidate candidate);


    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    Company toCompanyFromCreateRequest(CreateCompanyRequestDTO companyRequestDTO, @Context PasswordEncoder passwordEncoder);
    CreateCompanyResponseDTO toCreateResponseFromCompany(Company company);
    CompanyResponseDTO toResponseFromCompany(Company company);

    @Named("encodePassword")
    default String encodePassword(String password, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }
}
