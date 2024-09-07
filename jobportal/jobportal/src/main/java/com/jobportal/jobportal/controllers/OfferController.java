package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.offer.OfferCreateRequestDTO;
import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;
import com.jobportal.jobportal.services.offer.OfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<List<OfferResponseDTO>> getOffers(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String sortBy
            ){
        /*List<OfferResponseDTO> offerResponseDTOS;

        if(orderBy != null && sortBy != null){
            offerResponseDTOS = offerService.getAllOffers();
            Comparator<OfferResponseDTO> comparator =
        }else{
            return ResponseEntity.ok(offerService.getAllOffers());
        }*/
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponseDTO> getOffer(@PathVariable long offerId){
        return ResponseEntity.ok(offerService.getOffer(offerId));
    }

    @PostMapping
    public ResponseEntity<String> addOffer(@Valid @RequestBody OfferCreateRequestDTO requestDTO){
        offerService.addOffer(requestDTO);
        return new ResponseEntity<>("Created Offer successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<String> deleteOffer(@PathVariable long offerId){
        offerService.deleteOffer(offerId);
        return new ResponseEntity<>("Deleted Offer successfully!", HttpStatus.OK);
    }
}
