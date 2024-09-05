package com.jobportal.jobportal.mappers;

import com.jobportal.jobportal.dtos.candidate.CreateCandidateRequestDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateResponseDTO;
import com.jobportal.jobportal.entities.user.Admin;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.Company;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    AdminDTO toAdminDTO(Admin admin);
//
//    CompanyDTO toCompanyDTO(Company company);
//
//    CandidateDTO toCandidateDTO(Candidate candidate);
//
//    Admin toAdmin(AdminDTO adminDTO);
//
//    Company toCompany(CompanyDTO companyDTO);

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    Candidate toCandidateFromCreateRequest(CreateCandidateRequestDTO candidateDTO, @Context PasswordEncoder passwordEncoder);
    CreateCandidateResponseDTO toCreateCandidateResponseDTOFromCandidate(Candidate candidate);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateAdminFromDTO(AdminUpdateDTO dto, @MappingTarget Admin admin);
//
//    void updateCompanyFromDTO(CompanyUpdateDTO dto, @MappingTarget Company company);
//
//    void updateCandidateFromDTO(CandidateUpdateDTO dto, @MappingTarget Candidate candidate);

    @Named("encodePassword")
    default String encodePassword(String password, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }
}
