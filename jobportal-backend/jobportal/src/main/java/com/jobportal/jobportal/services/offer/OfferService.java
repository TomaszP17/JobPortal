package com.jobportal.jobportal.services.offer;

import com.jobportal.jobportal.dtos.offer.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfferService {

    List<OfferResponseDTO> getAllOffers(String orderBy, String sortBy);
    OfferDetailsResponseDTO getOffer(long offerId);
    void addOffer(OfferCreateRequestDTO requestDTO);
    void deleteOffer(long offerId);
    //List<OfferResponseDTO> getOfferByLocalizationName(String localization);
    Page<NextOfferResponseDTO> getNextOffers(Pageable pageable);
    OfferMarkerResponseDTO getMarkedOffer(long offerId);
}
