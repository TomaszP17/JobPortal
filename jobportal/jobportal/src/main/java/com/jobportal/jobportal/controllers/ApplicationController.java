package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.application.CreateApplicationRequestDTO;
import com.jobportal.jobportal.services.application.ApplicationServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationServiceImpl applicationService;
    public ApplicationController(ApplicationServiceImpl applicationService) {
        this.applicationService = applicationService;
    }
    @PostMapping
    public ResponseEntity<String> createApplication(@RequestBody CreateApplicationRequestDTO createApplicationRequestDTO){
        return new ResponseEntity<>(applicationService.applyToOffer(createApplicationRequestDTO), HttpStatus.CREATED);
    }
}
