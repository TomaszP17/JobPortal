package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeResponseDTO;
import com.jobportal.jobportal.services.employmenttype.EmploymentTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> addEmploymentType(@Valid @RequestBody EmploymentTypeCreateRequestDTO requestDTO){
        employmentTypeService.addEmploymentType(requestDTO);
        return new ResponseEntity<>("Created EmploymentType successfully!", HttpStatus.CREATED);
    }
}
