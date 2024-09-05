package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO;
import com.jobportal.jobportal.services.technology.TechnologyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
