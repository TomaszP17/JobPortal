package com.jobportal.jobportal.controllers

import com.jobportal.jobportal.dtos.company.CompanyResponseDTO
import com.jobportal.jobportal.dtos.company.CompanyResponseOfferStatsDTO
import com.jobportal.jobportal.dtos.company.CreateCompanyRequestDTO
import com.jobportal.jobportal.services.company.CompanyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class CompanyControllerTest extends Specification {

    def companyService = Mock(CompanyService)
    def controller = new CompanyController(companyService)

    def "should return all companies"(){
        given: "mocked service response"
        def companies = [new CompanyResponseDTO("Facebook", "1234567890", "facebook@gmail.com", "https://www.linkedin.com/in/tomaszp17/")]
        companyService.getAllCompanies() >> companies

        when: "the getAllCompanies method is called"
        ResponseEntity<List<CompanyResponseDTO>> response = controller.getAllCompanies()

        then: "the service is called and Http Status is OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == companies
    }

    def "should return specific company by Id"(){
        given: "mocked return company"
        def companyId = 1L
        def company = new CompanyResponseDTO("Facebook", "1234567890", "facebook@gmail.com", "https://www.linkedin.com/in/tomaszp17/")
        companyService.getCompanyById(companyId) >> company

        when: "the getCompanyById method is called"
        ResponseEntity<CompanyResponseDTO> response = controller.getCompanyById(companyId)

        then: "the service is called and Http Status is OK"
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == company
    }

    def "should add a new company"(){
        given: "a valid company create request"
        def request = new CreateCompanyRequestDTO("Facebook", "1234567890", "facebook@gmail.com", "https://www.linkedin.com/in/tomaszp17/")

        when: "the addCompany method is called"
        ResponseEntity<String> response = controller.createCompany(request)

        then: "the service is called and the HTTP status is CREATED"
        1 * companyService.createCompany(request)
        response.getStatusCode() == HttpStatus.CREATED
        response.getBody() == "Company Created Successfully!"
    }
}
