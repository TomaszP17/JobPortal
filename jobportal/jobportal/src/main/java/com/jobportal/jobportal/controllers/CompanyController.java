package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.company.CompanyResponseDTO;
import com.jobportal.jobportal.dtos.company.CompanyResponseOfferStatsDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyRequestDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyResponseDTO;
import com.jobportal.jobportal.services.company.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CreateCompanyResponseDTO> createCompany(@Valid @RequestBody CreateCompanyRequestDTO createCompanyRequestDTO){
        return new ResponseEntity<>(companyService.createCompany(createCompanyRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable Long id){
        return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/top-companies")
    public ResponseEntity<List<CompanyResponseOfferStatsDTO>> getCompaniesSortedBy(
            @RequestParam(defaultValue = "average") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        List<CompanyResponseOfferStatsDTO> companies = companyService.getCompaniesWithOfferStats(sortBy, page, size);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
}
