package com.jobportal.jobportal.controllers;


import com.jobportal.jobportal.dtos.candidate.CandidateResponseDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateRequestDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateResponseDTO;
import com.jobportal.jobportal.services.candidate.CandidateService;
import com.jobportal.jobportal.services.candidate.CandidateServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<CreateCandidateResponseDTO> createCandidate(@Valid @RequestBody CreateCandidateRequestDTO candidateRequestDTO){
        return new ResponseEntity<>(candidateService.createCandidate(candidateRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> getCandidateById(@PathVariable Long id){
        return new ResponseEntity<>(candidateService.getCandidateById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponseDTO>> getAllCandidates(){
        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }
}
