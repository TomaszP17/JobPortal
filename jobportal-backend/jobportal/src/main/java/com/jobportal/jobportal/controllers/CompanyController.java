package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.company.CompanyResponseDTO;
import com.jobportal.jobportal.dtos.company.CompanyResponseOfferStatsDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyFromOAuthRequestDTO;
import com.jobportal.jobportal.dtos.company.CreateCompanyRequestDTO;
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
    public ResponseEntity<String> createCompany(@Valid @RequestBody CreateCompanyRequestDTO createCompanyRequestDTO){
        companyService.createCompany(createCompanyRequestDTO);
        return new ResponseEntity<>("Company Created Successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/oauth")
    public ResponseEntity<String> createCompanyOauth(
            @Valid @RequestBody CreateCompanyFromOAuthRequestDTO requestDTO
            ){
        companyService.createCompanyFromOAuth(requestDTO);
        return new ResponseEntity<>("Company Created Successfully!", HttpStatus.CREATED);
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

    @PutMapping("/{companyId}")
    public ResponseEntity<Void> editCompany(@PathVariable Long companyId,
                                            @Valid @RequestBody CreateCompanyRequestDTO createCompanyRequestDTO){
        companyService.updateCompany(companyId, createCompanyRequestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
