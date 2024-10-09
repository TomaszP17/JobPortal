package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.offer.OfferTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferTechnologyRepository extends JpaRepository<OfferTechnology, Long> {
    List<OfferTechnology> findAllByOfferId(Long offerId);
}
