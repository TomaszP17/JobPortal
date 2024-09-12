package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.geocoding.GeocodingResponseDTO;
import com.jobportal.jobportal.services.geocoding.GeocodingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/geocoding")
public class GeocodingController {

    private final GeocodingService geocodingService;

    public GeocodingController(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @GetMapping
    public ResponseEntity<Mono<GeocodingResponseDTO>> getCoordinates(@RequestParam String address) {
        return new ResponseEntity<>(geocodingService.fetchCoordinates(address), HttpStatus.OK);
    }

}
