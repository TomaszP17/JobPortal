package com.jobportal.jobportal.controllers

import com.jobportal.jobportal.dtos.candidate.CandidateResponseDTO
import com.jobportal.jobportal.dtos.candidate.CreateCandidateRequestDTO
import com.jobportal.jobportal.services.candidate.CandidateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class CandidateControllerTest extends Specification {

    def candidateService = Mock(CandidateService)
    def controller = new CandidateController(candidateService)

    def "should return all candidates"(){
        given: "mocked service response"
        def candidates = [new CandidateResponseDTO("Tomasz", "Pluciński", 5, "tplucinski15@gmail.com", "https://github.com/TomaszP17", "https://www.linkedin.com/in/tomaszp17/")]
        candidateService.getAllCandidates() >> candidates

        when: "the getAllCandidates method is called"
        ResponseEntity<List<CandidateResponseDTO>> response = controller.getAllCandidates()

        then: "the service returns the correct candidates and HTTP status is OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == candidates
    }

    def "should return a specific candidate by ID"(){
        given: "mocked service response"
        def candidateId = 1L
        def candidate = new CandidateResponseDTO("Tomasz", "Pluciński", 5, "tplucinski15@gmail.com", "https://github.com/TomaszP17", "https://www.linkedin.com/in/tomaszp17/")
        candidateService.getCandidateById(candidateId) >> candidate

        when: "the getCandidateById method is called"
        ResponseEntity<CandidateResponseDTO> response = controller.getCandidateById(candidateId)

        then: "the service returns the correct candidate and HTTP status is OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == candidate
    }

    def "should add new candidate"(){
        given: "a valid candidate create request"
        def request = new CreateCandidateRequestDTO("Tomasz", "Plucinski", "tplucinski15@gmail.com", "a1B@password")

        when: "the createCandidate method is called"
        ResponseEntity<String> response = controller.createCandidate(request)

        then: "the service is called and the HTTP status is CREATED"
        1 * candidateService.createCandidate(request)
        response.getStatusCode() == HttpStatus.CREATED
        response.getBody() == "Candidate Created Successfully!"
    }

}
