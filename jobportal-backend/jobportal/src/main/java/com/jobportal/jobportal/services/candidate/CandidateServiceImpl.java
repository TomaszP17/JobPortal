package com.jobportal.jobportal.services.candidate;

import com.jobportal.jobportal.dtos.candidate.*;
import com.jobportal.jobportal.entities.user.*;
import com.jobportal.jobportal.exceptions.authority.AuthorityDoesNotExistException;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.mappers.UserMapper;
import com.jobportal.jobportal.repositories.AuthorityRepository;
import com.jobportal.jobportal.repositories.CandidateRepository;
import com.jobportal.jobportal.repositories.UserAuthorityRepository;
import com.jobportal.jobportal.services.pdf.PdfService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService{

    private final CandidateRepository candidateRepository;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final PdfService pdfService;

    public CandidateServiceImpl(CandidateRepository candidateRepository,
                                UserMapper userMapper,
                                AuthorityRepository authorityRepository,
                                UserAuthorityRepository userAuthorityRepository,
                                PasswordEncoder passwordEncoder, PdfService pdfService) {
        this.candidateRepository = candidateRepository;
        this.userMapper = userMapper;
        this.authorityRepository = authorityRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.pdfService = pdfService;
    }

    @Transactional
    @Override
    public CreateCandidateResponseDTO createCandidate(CreateCandidateRequestDTO createCandidateRequestDTO) {
        Candidate candidate = userMapper.toCandidateFromCreateRequest(createCandidateRequestDTO, passwordEncoder);
        candidate = candidateRepository.save(candidate);

        Authority authority = authorityRepository.findByName("CANDIDATE");

        if (authority == null){
            throw new AuthorityDoesNotExistException("The authority named: CANDIDATE does not exist");
        }

        UserAuthority userAuthority = UserAuthority.builder()
                .user(candidate)
                .authority(authority)
                .build();

        userAuthorityRepository.save(userAuthority);

        return userMapper.toCreateResponseFromCandidate(candidate);
    }

    @Override
    public CandidateResponseDTO getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new UserDoesNotExistException("Candidate with id: " + id + " does not exist"));

        return userMapper.toResponseFromCandidate(candidate);
    }

    @Override
    public List<CandidateResponseDTO> getAllCandidates() {
        return candidateRepository.findAll().stream().map(userMapper::toResponseFromCandidate).toList();
    }

    @Transactional
    @Override
    public void editCandidate(Long id, CreateCandidateRequestDTO candidateRequestDTO) {
        Candidate existingCandidate = candidateRepository.findById(id)
                .orElseThrow(() -> new UserDoesNotExistException("Candidate with id: " + id + " does not exist"));

        System.err.println("Found candidate: " + existingCandidate);

        userMapper.updateCandidateFromCreateRequest(candidateRequestDTO, existingCandidate, passwordEncoder);

        System.err.println("Edited candidate: " + existingCandidate);
        candidateRepository.save(existingCandidate);

    }


}
