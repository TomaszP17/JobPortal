package com.jobportal.jobportal.controllers

import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeCreateRequestDTO
import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeResponseDTO
import com.jobportal.jobportal.services.employmenttype.EmploymentTypeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class EmploymentTypeControllerTest extends Specification {

    def employmentService = Mock(EmploymentTypeService)
    def controller = new EmploymentTypeController(employmentService)

    def "should return all employment types"(){

        given: "mocked service response"
        def employmentType = [new EmploymentTypeResponseDTO(1L, "B2B")]
        employmentService.getAllEmploymentTypes() >> employmentType

        when: "the getAllEmploymentTypes method is called"
        ResponseEntity<List<EmploymentTypeResponseDTO>> response = controller.getAllEmploymentTypes()

        then: "the service returns the correct employment types and Http Status OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == employmentType
    }

    def "should return a specific experience by ID"(){
        given: "mocked service response for a specific employment Type"
        def employmentTypeId = 1L
        def employmentType = new EmploymentTypeResponseDTO(employmentTypeId, "Internship")
        employmentService.getEmploymentType(employmentTypeId) >> employmentType

        when: "the getEmploymentType method is called"
        ResponseEntity<EmploymentTypeResponseDTO> response = controller.getEmploymentType(employmentTypeId)

        then: "the service returns the correct employmentType and HTTP status OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == employmentType
    }

    def "should add a new employmentType"(){
        given: "a valid employmentType create request"
        def request = new EmploymentTypeCreateRequestDTO("B2B")

        when: "the addEmploymentType method is called"
        ResponseEntity<String> response = controller.addEmploymentType(request)

        then: "the service is called and the HTTP status is CREATED"
        1 * employmentService.addEmploymentType(request)
        response.getStatusCode() == HttpStatus.CREATED
        response.getBody() == "Created EmploymentType successfully!"
    }

    def "should delete a employmentType"(){
        given: "a valid employmentType ID"
        def employmentTypeId = 1L

        when: "the deleteEmploymentType method is called"
        ResponseEntity<String> response = controller.deleteEmploymentType(employmentTypeId)

        then: "the service is called to delete the employmentType and HTTP status is OK"
        1 * employmentService.deleteEmploymentType(employmentTypeId)
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == "Deleted EmploymentType successfully!"
    }
}
