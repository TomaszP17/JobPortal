package com.jobportal.jobportal.services.offer;

import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;
import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.exceptions.offer.OfferDoesNotExistsException;
import com.jobportal.jobportal.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimilarOfferImpl implements SimilarOffer{

    private OfferRepository offerRepository;

    public SimilarOfferImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    /**
     * Title - 0.5
     * Technology - 0.4
     * Experience - 0.3
     * Salary Min-Max - 0.2
     * Localization - 0.1
     * @param offerId - offer for which we want similar offers
     * @param offerCount - count of looking offers
     * @return list of similar offers
     */
    @Override
    public List<OfferResponseDTO> getSimilarOffers(long offerId, int offerCount) {

        Map<Long, Double> similarOffers = new HashMap<>();

        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() -> new OfferDoesNotExistsException("Offer with that id: {" + offerId + "does not exists"));



        return null;
    }

}
