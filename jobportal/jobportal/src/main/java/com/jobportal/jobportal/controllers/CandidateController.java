package com.jobportal.jobportal.controllers;


import com.jobportal.jobportal.dtos.candidate.CreateCandidateRequestDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateResponseDTO;
import com.jobportal.jobportal.services.candidate.CandidateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateServiceImpl candidateService;

    public CandidateController(CandidateServiceImpl candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping()
    public ResponseEntity<CreateCandidateResponseDTO> createCandidate(@RequestBody CreateCandidateRequestDTO candidateRequestDTO){
        return new ResponseEntity<>(candidateService.createCandidate(candidateRequestDTO), HttpStatus.CREATED);
    }
}
