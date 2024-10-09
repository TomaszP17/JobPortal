package com.jobportal.jobportal.services.offer;

import com.jobportal.jobportal.dtos.offer.OfferCreateRequestDTO;
import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfferService {

    List<OfferResponseDTO> getAllOffers(String orderBy, String sortBy);
    OfferResponseDTO getOffer(long offerId);
    void addOffer(OfferCreateRequestDTO requestDTO);
    void deleteOffer(long offerId);
    Page<OfferResponseDTO> getNextOffers(Pageable pageable);
    List<OfferResponseDTO> getOffersByFilter(String localization, Integer minSalary, Integer maxSalary, List<Integer> workTypeIds, List<Integer> technologiesIds, List<Integer> experienceIds, List<Integer> employmentsTypeIds);
}
