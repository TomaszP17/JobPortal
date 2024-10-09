package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.offer.OfferEmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferEmploymentTypeRepository extends JpaRepository<OfferEmploymentType, Long> {
    List<OfferEmploymentType> findAllByOfferId(long offerId);
}
