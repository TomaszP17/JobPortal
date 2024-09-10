package com.jobportal.jobportal.controllers

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

        when "the getAllEmploymentTypes method is called"
        ResponseEntity<List<EmploymentTypeResponseDTO>> response = controller.getAllEmploymentTypes()

        then: "the service returns the correct employment types and Http Status OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == employmentType
    }

    def "should return a specific experience by ID"(){
        given: "mocked service response for a specific employment Type"
        def employmentTypeId = 1L
        def employmentType = new EmploymentTypeResponseDTO(employmentTypeId, "Internship")

        when ""
    }

}
