package com.jobportal.jobportal.services;

import com.jobportal.jobportal.dtos.offer.OfferResponseModel;
import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.exceptions.OfferDoesNotExistsException;
import com.jobportal.jobportal.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<OfferResponseModel> getAllOffers() {
        return offerRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public OfferResponseModel getOffer(long offerId) {
        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() -> new OfferDoesNotExistsException("Offer does not exists"));

        return convertToDTO(offer);
    }

    private OfferResponseModel convertToDTO(Offer offer){
        return new OfferResponseModel(
                offer.getId(),
                offer.getTitle(),
                offer.getExpiryDate(),
                offer.getSalaryMin(),
                offer.getSalaryMax(),
                offer.getDescription()
        );
    }
}
