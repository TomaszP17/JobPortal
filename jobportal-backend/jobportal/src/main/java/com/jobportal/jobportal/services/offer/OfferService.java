package com.jobportal.jobportal.services.offer;

import com.jobportal.jobportal.dtos.offer.OfferCreateRequestDTO;
import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;

import java.util.List;

public interface OfferService {
    List<OfferResponseDTO> getAllOffers(String orderBy, String sortBy);
    OfferResponseDTO getOffer(long offerId);
    void addOffer(OfferCreateRequestDTO requestDTO);
    void deleteOffer(long offerId);
    //List<OfferResponseDTO> getOfferByLocalizationName(String localization);
}
