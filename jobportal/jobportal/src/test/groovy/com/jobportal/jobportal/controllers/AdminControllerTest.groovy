package com.jobportal.jobportal.controllers

import com.jobportal.jobportal.dtos.admin.AdminResponseDTO
import com.jobportal.jobportal.dtos.admin.CreateAdminRequestDTO
import com.jobportal.jobportal.services.admin.AdminService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class AdminControllerTest extends Specification {

    def adminService = Mock(AdminService)
    def controller = new AdminController(adminService)

    def "should return all admins"(){
        given: "mocked service response"
        def admins = [new AdminResponseDTO(1L, "Admin")]
        adminService.getAllAdmins() >> admins

        when: "the getAllAdmins method is called"
        ResponseEntity<List<AdminResponseDTO>> response = controller.getAllAdmins()

        then: "the service returns the correct admins and Http status is OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == admins
    }

    def "should add new admin"(){
        given: "a valid admin create request"
        def request = new CreateAdminRequestDTO("Admin", "admin@gmail.com", "a1@Bpassword")

        when: "the addAdmin method is called"
        ResponseEntity<String> response = controller.createAdmin(request)

        then: "the service is called and the HTTP status is CREATED"
        1 * adminService.createAdmin(request)
        response.getStatusCode() == HttpStatus.CREATED
        response.getBody() == "Create Admin Successfully!"
    }
}
