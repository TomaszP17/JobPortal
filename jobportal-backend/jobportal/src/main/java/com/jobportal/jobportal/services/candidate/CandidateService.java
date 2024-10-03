package com.jobportal.jobportal.services.candidate;

import com.jobportal.jobportal.dtos.candidate.*;
import com.jobportal.jobportal.entities.user.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateService {

    CreateCandidateResponseDTO createCandidate(CreateCandidateRequestDTO createCandidateRequestDTO);
    CandidateResponseDTO getCandidateById(Long id);
    List<CandidateResponseDTO> getAllCandidates();
    /*UpdateCandidateResponseDTO updateCandidate(Long id,
                                               MultipartFile pdf,
                                               UpdateCandidateRequestDTO updateCandidateRequestDTO);
    Candidate createCandidateFromOAuth(String email);

                                               UpdateCandidateRequestDTO updateCandidateRequestDTO);*/
    void editCandidate(Long id, CreateCandidateRequestDTO candidateRequestDTO);
}
