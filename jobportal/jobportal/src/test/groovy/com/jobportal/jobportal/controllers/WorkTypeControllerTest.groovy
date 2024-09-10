package com.jobportal.jobportal.controllers

import com.jobportal.jobportal.dtos.worktype.WorkTypeCreateRequestDTO
import com.jobportal.jobportal.dtos.worktype.WorkTypeResponseDTO
import com.jobportal.jobportal.services.worktype.WorkTypeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class WorkTypeControllerTest extends Specification {

    def workTypeService = Mock(WorkTypeService)
    def controller = new WorkTypeController(workTypeService)

    def "should return all workTypes"(){
        given: "mocked service response"
        def workTypes = [new WorkTypeResponseDTO(1L, "Digital")]
        workTypeService.getAllWorkTypes() >> workTypes

        when: "the getAllWorkTypes method is called"
        ResponseEntity<List<WorkTypeResponseDTO>> response = controller.getAllWorkTypes()

        then: "the service returns the correct worktypes and HTTP status is OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == workTypes
    }

    def "should return a specific worktype by ID"(){
        given: "mocked service response for a specific worktype"
        def workTypeId = 1L
        def workType = new WorkTypeResponseDTO(workTypeId, "Digital")
        workTypeService.getWorkType(workTypeId) >> workType

        when: "the getWorkType method is called"
        ResponseEntity<WorkTypeResponseDTO> response = controller.getWorkType(workTypeId)

        then: "the service returns the correct workType and HTTP status is OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == workType
    }

    def "should add new worktype"(){
        given: "a valid worktype create request"
        def request = new WorkTypeCreateRequestDTO("Full Time")

        when: "the addWorkType method is called"
        ResponseEntity<String> response = controller.addWorkType(request)

        then: "the service is called and the HTTP status is CREATED"
        1 * workTypeService.addWorkType(request)
        response.getStatusCode() == HttpStatus.CREATED
        response.getBody() == "WorkType added successfully"
    }

    def "should delete a worktype"(){
        given: "a valid worktype ID"
        def workTypeId = 1L

        when: "the deleteWorkType method is called"
        ResponseEntity<String> response = controller.deleteWorkType(workTypeId)

        then: "the service is called and the HTTP status is OK"
        1 * workTypeService.deleteWorkType(workTypeId)
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == "WorkType deleted successfully"
    }
}
