package com.jobportal.jobportal.controllers


import com.jobportal.jobportal.dtos.technology.TechnologyCreateRequestDTO
import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO
import com.jobportal.jobportal.services.technology.TechnologyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class TechnologyControllerTest extends Specification {

    def technologyService = Mock(TechnologyService)
    def controller = new TechnologyController(technologyService)

    def "should return all technologies"() {
        given: "mocked service response"
        def technologies = [new TechnologyResponseDTO(1L, "Java")]
        technologyService.getTechnologies() >> technologies

        when: "the getAllTechnologies method is called"
        ResponseEntity<List<TechnologyResponseDTO>> response = controller.getAllTechnologies()

        then: "the service returns the correct technologies and Http status is OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == technologies
    }

    def "should return a specific technology by ID"() {
        given: "mocked service response for a specific technology"
        def technologyId = 1L
        def technology = new TechnologyResponseDTO(technologyId, "Java")
        technologyService.getTechnology(technologyId) >> technology

        when: "the getTechnology method is called"
        ResponseEntity<TechnologyResponseDTO> response = controller.getTechnology(technologyId)

        then: "the service returns the correct technology and HTTP status is OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == technology
    }

    def "should add a new technology"() {
        given: "a valid technology create request"
        def request = new TechnologyCreateRequestDTO("GoLang")

        when: "the addTechnology method is called"
        ResponseEntity<String> response = controller.addTechnology(request)

        then: "the service is called and the HTTP status is CREATED"
        1 * technologyService.addTechnology(request)
        response.getStatusCode() == HttpStatus.CREATED
        response.getBody() == "Technology added successfully"
    }

    def "should delete a technology"() {
        given: "a valid technology ID"
        def technologyId = 1L

        when: "the deleteTechnology method is called"
        ResponseEntity<String> response = controller.deleteTechnology(technologyId)

        then: "the service is called to delete the technology and HTTP status is OK"
        1 * technologyService.deleteTechnology(technologyId)
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == "Technology deleted successfully"
    }

}
