package com.jobportal.jobportal.services.offer;

import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;

import java.util.List;

public interface SimilarOffer {
    List<OfferResponseDTO> getSimilarOffers(long offerId, int offerCount);
}
