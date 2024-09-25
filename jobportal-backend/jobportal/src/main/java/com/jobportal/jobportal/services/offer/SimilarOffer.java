package com.jobportal.jobportal.services.offer;

import com.jobportal.jobportal.dtos.offer.SimilarOfferResponseDTO;

import java.util.List;

public interface SimilarOffer {
    List<SimilarOfferResponseDTO> getSimilarOffers(long offerId, int offerCount);
}
