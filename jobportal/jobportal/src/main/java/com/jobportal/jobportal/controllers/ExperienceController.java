package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.experience.ExperienceCreateRequestDTO;
import com.jobportal.jobportal.dtos.experience.ExperienceResponseDTO;
import com.jobportal.jobportal.services.experience.ExperienceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienceResponseDTO>> getAllExperiences(){
        return new ResponseEntity<>(experienceService.getAllExperiences(), HttpStatus.OK);
    }

    @GetMapping("/{experienceId}")
    public ResponseEntity<ExperienceResponseDTO> getExperience(@PathVariable long experienceId){
        return new ResponseEntity<>(experienceService.getExperience(experienceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addExperience(@Valid @RequestBody ExperienceCreateRequestDTO requestDTO){
        experienceService.addExperience(requestDTO);
        return new ResponseEntity<>("Created experience successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/{experienceId}")
    public ResponseEntity<String> deleteExperience(@PathVariable long experienceId){
        experienceService.deleteExperience(experienceId);
        return new ResponseEntity<>("Experience deleted successfully!", HttpStatus.OK);
    }
}
