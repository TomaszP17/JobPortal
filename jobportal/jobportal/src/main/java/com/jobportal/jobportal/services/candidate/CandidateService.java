package com.jobportal.jobportal.services.candidate;

import com.jobportal.jobportal.dtos.candidate.CreateCandidateRequestDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateResponseDTO;

public interface CandidateService {

    CreateCandidateResponseDTO createCandidate(CreateCandidateRequestDTO createCandidateRequestDTO);

}
