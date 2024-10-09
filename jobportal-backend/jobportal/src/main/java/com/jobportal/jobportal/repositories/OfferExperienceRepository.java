package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.offer.OfferExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferExperienceRepository extends JpaRepository<OfferExperience, Long> {
    List<OfferExperience> findAllByOfferId(long offerId);
}
