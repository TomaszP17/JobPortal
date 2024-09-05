package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.technology.TechnologyCreateRequestDTO;
import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO;
import com.jobportal.jobportal.services.technology.TechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping
    public ResponseEntity<List<TechnologyResponseDTO>> getAllTechnologies(){
        return new ResponseEntity<>(technologyService.getTechnologies(), HttpStatus.OK);
    }

    @GetMapping("/{technologyId}")
    public ResponseEntity<TechnologyResponseDTO> getTechnology(@PathVariable long technologyId){
        return new ResponseEntity<>(technologyService.getTechnology(technologyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addTechnology(@Valid @RequestBody TechnologyCreateRequestDTO requestDTO){
        technologyService.addTechnology(requestDTO);
        return new ResponseEntity<>("Technology added successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{technologyId}")
    public ResponseEntity<String> deleteTechnology(@PathVariable long technologyId){
        technologyService.deleteTechnology(technologyId);
        return new ResponseEntity<>("Technology deleted successfully", HttpStatus.OK);
    }
}
