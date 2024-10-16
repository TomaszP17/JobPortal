package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.offer.*;
import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.services.offer.OfferService;
import com.jobportal.jobportal.services.offer.SimilarOffer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;
    private final SimilarOffer similarOffer;

    public OfferController(OfferService offerService, SimilarOffer similarOffer) {
        this.offerService = offerService;
        this.similarOffer = similarOffer;
    }

    @GetMapping
    public ResponseEntity<List<OfferResponseDTO>> getOffers(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String sortBy
    ){
        return new ResponseEntity<>(offerService.getAllOffers(orderBy, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferDetailsResponseDTO> getOffer(@PathVariable long offerId){
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

    @GetMapping("/filter")
    public List<Offer> getOffersByLocalization(
            @RequestParam(required = false) String localization
    ){
        return null;
    }

    @GetMapping("/similar-offers")
    public ResponseEntity<List<SimilarOfferResponseDTO>> getSimilarOffers(@RequestParam long offerId,@RequestParam int offerCount){
        return new ResponseEntity<>(similarOffer.getSimilarOffers(offerId, offerCount), HttpStatus.OK);
    }

    @GetMapping("/next-offers")
    public ResponseEntity<Page<NextOfferResponseDTO>> getNextOffers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<NextOfferResponseDTO> offersPage = offerService.getNextOffers(pageable);
        return new ResponseEntity<>(offersPage, HttpStatus.OK);
    }

    @GetMapping("/marker/{offerId}")
    public ResponseEntity<OfferMarkerResponseDTO> getOfferMarkedOnMap(@PathVariable long offerId){
        return new ResponseEntity<>(offerService.getMarkedOffer(offerId), HttpStatus.OK);
    }
}
