package com.jobportal.jobportal.controllers;


import com.jobportal.jobportal.dtos.candidate.CandidateResponseDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateFromAuthRequestDTO;
import com.jobportal.jobportal.dtos.candidate.CreateCandidateRequestDTO;
import com.jobportal.jobportal.services.candidate.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<String> createCandidate(@Valid @RequestBody CreateCandidateRequestDTO candidateRequestDTO){
        candidateService.createCandidate(candidateRequestDTO);
        return new ResponseEntity<>("Candidate Created Successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/oauth")
    public ResponseEntity<?> createCandidateOauth(
            @Valid @RequestBody CreateCandidateFromAuthRequestDTO candidateRequestDTO){
        candidateService.createCandidateFromAuth(candidateRequestDTO);
        return new ResponseEntity<>("Candidate Created Successfully!", HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> getCandidateById(@PathVariable Long id){
        return new ResponseEntity<>(candidateService.getCandidateById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponseDTO>> getAllCandidates(){
        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editCandidate(@PathVariable Long id, @Valid @RequestBody CreateCandidateRequestDTO candidateRequestDTO){
        candidateService.editCandidate(id, candidateRequestDTO);
        return new ResponseEntity<>("Candidate Updated Successfully!", HttpStatus.NO_CONTENT);
    }
}
