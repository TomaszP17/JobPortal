package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.localization.LocalizationCoordinatesResponseDTO;
import com.jobportal.jobportal.services.localization.LocalizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/localization")
public class LocalizationController {

    private final LocalizationService localizationService;

    public LocalizationController(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @GetMapping
    public ResponseEntity<List<LocalizationCoordinatesResponseDTO>> getOffersLocalizationAndOfferId(){
        return new ResponseEntity<>(localizationService.getAllLocalizationsAndOfferIds(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLocalizations(){
        return new ResponseEntity<>(localizationService.getAllLocalizations(), HttpStatus.OK);
    }
}
