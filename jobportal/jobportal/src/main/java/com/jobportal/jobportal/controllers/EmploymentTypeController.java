package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeResponseDTO;
import com.jobportal.jobportal.services.employmenttype.EmploymentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employment-type")
public class EmploymentTypeController {

    private final EmploymentTypeService employmentTypeService;

    public EmploymentTypeController(EmploymentTypeService employmentTypeService) {
        this.employmentTypeService = employmentTypeService;
    }

    @GetMapping
    public ResponseEntity<List<EmploymentTypeResponseDTO>> getAllEmploymentTypes(){
        return new ResponseEntity<>(employmentTypeService.getAllEmploymentTypes(), HttpStatus.OK);
    }

    @GetMapping("/{employmentTypeId}")
    public ResponseEntity<EmploymentTypeResponseDTO> getEmploymentType(@PathVariable long employmentTypeId){
        return new ResponseEntity<>(employmentTypeService.getEmploymentType(employmentTypeId), HttpStatus.OK);
    }
}
