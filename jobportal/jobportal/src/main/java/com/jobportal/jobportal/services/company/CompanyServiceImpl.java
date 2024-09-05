package com.jobportal.jobportal.services.company;

import com.jobportal.jobportal.dtos.company.CreateCompanyRequestDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyResponseDTO;
import com.jobportal.jobportal.entities.user.Authority;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.Company;
import com.jobportal.jobportal.entities.user.UserAuthority;
import com.jobportal.jobportal.exceptions.authority.AuthorityDoesNotExistException;
import com.jobportal.jobportal.mappers.UserMapper;
import com.jobportal.jobportal.repositories.AuthorityRepository;
import com.jobportal.jobportal.repositories.CandidateRepository;
import com.jobportal.jobportal.repositories.CompanyRepository;
import com.jobportal.jobportal.repositories.UserAuthorityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private UserMapper userMapper;
    private final AuthorityRepository authorityRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyServiceImpl(CompanyRepository companyRepository, AuthorityRepository authorityRepository, UserAuthorityRepository userAuthorityRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.authorityRepository = authorityRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public CreateCompanyResponseDTO createCompany(CreateCompanyRequestDTO createCompanyRequestDTO) {
        Company company = userMapper.toCompanyFromCreateRequest(createCompanyRequestDTO, passwordEncoder);
        company = companyRepository.save(company);

        Authority authority = authorityRepository.findByName("CANDIDATE");

        if (authority == null){
            throw new AuthorityDoesNotExistException("The authority named: CANDIDATE does not exist");
        }

        UserAuthority userAuthority = UserAuthority.builder()
                .user(company)
                .authority(authority)
                .build();

        userAuthorityRepository.save(userAuthority);

        return userMapper.toCreateResponseFromCompany(company);
    }
}
