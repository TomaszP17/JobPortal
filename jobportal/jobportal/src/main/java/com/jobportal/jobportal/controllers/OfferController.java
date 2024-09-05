package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;
import com.jobportal.jobportal.services.offer.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<List<OfferResponseDTO>> getOffers(){
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponseDTO> getOffer(@PathVariable long offerId){
        return ResponseEntity.ok(offerService.getOffer(offerId));
    }

    @PostMapping
    public ResponseEntity<?> addOffer(){
        return ResponseEntity.status(HttpStatus.CREATED).body(offerService.getAllOffers());
    }
}
