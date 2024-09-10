package com.jobportal.jobportal.controllers

import com.jobportal.jobportal.dtos.experience.ExperienceCreateRequestDTO
import com.jobportal.jobportal.dtos.experience.ExperienceResponseDTO
import com.jobportal.jobportal.services.experience.ExperienceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class ExperienceControllerTest extends Specification {

    def experienceService = Mock(ExperienceService)
    def controller = new ExperienceController(experienceService);

    def "should return all experiences"(){
        given: "mocked service response"
        def experiences = [new ExperienceResponseDTO(1L, "Junior")]

        when "the getAllExperiences method is called"
        ResponseEntity<List<ExperienceResponseDTO>> response = controller.getAllExperiences()

        then: "the service returns the correct experiences and Http Status OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == experiences
    }

    def "should return a specific experience by ID"(){
        given: "mocked service response for a specific experience"
        def experienceId = 1L
        def experience = new ExperienceResponseDTO(experienceId, "Junior")
        experienceService.getExperience(experienceId) >> experience

        when: "the getExperience method is called"
        ResponseEntity<ExperienceResponseDTO> response = controller.getExperience(experienceId)

        then: "the service returns the correct experience and HttpStatus OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == experience
    }

    def "should add a new experience"(){
        given: "a valid experience create request"
        def request = new ExperienceCreateRequestDTO("Senior")

        when: "the addExperience method is called"
        ResponseEntity<String> response = controller.addExperience(request)

        then: "the service is called and the Http status"
        1 * experienceService.addExperience(request)
        response.getStatusCode() == HttpStatus.CREATED
        response.getBody() == "Technology added successfully"
    }

    def "should delete a experience"(){
        given: "a valid experience ID"
        def experienceId = 1L

        when: "the deleteExperience method is called"
        ResponseEntity<String> response = controller.deleteExperience(experienceId)

        then: "the service is called to delete the experience and HTTP OK"
        1 * experienceService.deleteExperience(experienceId)
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == "Experience deleted successfully"
    }
}
