package com.jobportal.jobportal.services.offer;

import com.jobportal.jobportal.dtos.offer.SimilarOfferResponseDTO;
import com.jobportal.jobportal.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimilarOfferImpl implements SimilarOffer {

    private final OfferRepository offerRepository;

    public SimilarOfferImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<SimilarOfferResponseDTO> getSimilarOffers(long offerId, int offerCount) {
        List<Object[]> results = offerRepository.getSimilarOffersWithDetails(offerId, offerCount);

        return results.stream().map(result -> {
            SimilarOfferResponseDTO dto = new SimilarOfferResponseDTO();
            dto.setId((Long) result[0]);
            dto.setTitle((String) result[1]);
            dto.setExpiryDate((Timestamp) result[2]);
            dto.setSalaryMin((Integer) result[3]);
            dto.setSalaryMax((Integer) result[4]);
            dto.setDescription((String) result[5]);
            dto.setSimilarityScore((Double) result[6]);
            return dto;
        }).collect(Collectors.toList());
    }
}

