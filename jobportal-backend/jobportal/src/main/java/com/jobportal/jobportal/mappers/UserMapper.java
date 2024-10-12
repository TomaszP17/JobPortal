package com.jobportal.jobportal.mappers;

import com.jobportal.jobportal.dtos.admin.AdminResponseDTO;
import com.jobportal.jobportal.dtos.admin.CreateAdminRequestDTO;
import com.jobportal.jobportal.dtos.admin.CreateAdminResponseDTO;
import com.jobportal.jobportal.dtos.candidate.*;
import com.jobportal.jobportal.dtos.company.*;
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
    UpdateCandidateResponseDTO toUpdateResponseFromCandidate(Candidate candidate);
    Candidate toCandidateFromRequestOAuthRequest(CreateCandidateFromAuthRequestDTO createCandidateFromAuthRequestDTO);
    CreateCandidateFromOAuthResponseDTO toCreateResponseFromOAuthRequest(Candidate candidate);

    @Mapping(target = "role", source = "role")
    CurrentUserCandidateDTO toCurrentUserCandidateDTOFromCurrentUser(Candidate currentUser, String role);

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    void updateCandidateFromCreateRequest(CreateCandidateRequestDTO candidateDTO, @MappingTarget Candidate candidate, @Context PasswordEncoder passwordEncoder);



    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    Company toCompanyFromCreateRequest(CreateCompanyRequestDTO companyRequestDTO, @Context PasswordEncoder passwordEncoder);
    CreateCompanyResponseDTO toCreateResponseFromCompany(Company company);
    CompanyResponseDTO toResponseFromCompany(Company company);
    Company toCompanyFromRequestOAuth(CreateCompanyFromOAuthRequestDTO requestDTO);

    @Mapping(target = "role", source = "role")
    CurrentUserCompanyDTO toCurrentUserCompanyDTOFromCurrentUser(Company company, String role);

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    void updateCompanyFromCreateRequest(CreateCompanyRequestDTO companyRequestDTO, @MappingTarget Company company, @Context PasswordEncoder passwordEncoder);

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    Admin toAdminFromCreateRequest(CreateAdminRequestDTO createAdminRequestDTO, @Context PasswordEncoder passwordEncoder);
    CreateAdminResponseDTO toCreateResponseFromAdmin(Admin admin);
    Admin toAdminFromResponseDTO(AdminResponseDTO responseDTO);
    AdminResponseDTO toAdminResponseDTOFromAdmin(Admin admin);

    @Named("encodePassword")
    default String encodePassword(String password, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }
}
