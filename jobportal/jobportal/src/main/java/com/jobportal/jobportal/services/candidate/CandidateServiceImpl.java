package com.jobportal.jobportal.services.candidate;

import com.jobportal.jobportal.dtos.candidate.CreateCandidateRequestDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateResponseDTO;
import com.jobportal.jobportal.entities.user.Admin;
import com.jobportal.jobportal.entities.user.Authority;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.UserAuthority;
import com.jobportal.jobportal.mappers.UserMapper;
import com.jobportal.jobportal.repositories.AuthorityRepository;
import com.jobportal.jobportal.repositories.CandidateRepository;
import com.jobportal.jobportal.repositories.UserAuthorityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandidateServiceImpl implements CandidateService{

    private final CandidateRepository candidateRepository;
    private UserMapper userMapper;
    private final AuthorityRepository authorityRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    public CandidateServiceImpl(CandidateRepository candidateRepository,
                                UserMapper userMapper,
                                AuthorityRepository authorityRepository,
                                UserAuthorityRepository userAuthorityRepository,
                                PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.userMapper = userMapper;
        this.authorityRepository = authorityRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public CreateCandidateResponseDTO createCandidate(CreateCandidateRequestDTO createCandidateRequestDTO) {
        Candidate candidate = userMapper.toCandidateFromCreateRequest(createCandidateRequestDTO, passwordEncoder);
        candidate = candidateRepository.save(candidate);

        Authority authority = authorityRepository.findByName("CANDIDATE");

        UserAuthority userAuthority = UserAuthority.builder()
                .user(candidate)
                .authority(authority)
                .build();

        userAuthorityRepository.save(userAuthority);

        return userMapper.toCreateCandidateResponseDTOFromCandidate(candidate);
    }
}
