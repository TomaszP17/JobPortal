package com.jobportal.jobportal.services.candidate;

import com.jobportal.jobportal.dtos.candidate.*;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateService {

    CreateCandidateResponseDTO createCandidate(CreateCandidateRequestDTO createCandidateRequestDTO);
    CandidateResponseDTO getCandidateById(Long id);
    List<CandidateResponseDTO> getAllCandidates();
    Candidate createCandidateFromOAuth(String email);
    void editCandidate(Long id, CreateCandidateRequestDTO candidateRequestDTO);

    Candidate createCandidateFromOAuth(String email);
}
