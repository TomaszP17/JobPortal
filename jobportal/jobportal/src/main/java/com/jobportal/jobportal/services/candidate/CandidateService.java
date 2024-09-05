package com.jobportal.jobportal.services.candidate;

import com.jobportal.jobportal.dtos.candidate.CandidateResponseDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateRequestDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateResponseDTO;

import java.util.List;

public interface CandidateService {

    CreateCandidateResponseDTO createCandidate(CreateCandidateRequestDTO createCandidateRequestDTO);
    CandidateResponseDTO getCandidateById(Long id);
    List<CandidateResponseDTO> getAllCandidates();

}
