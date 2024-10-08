package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.offer.OfferWorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferWorkTypeRepository extends JpaRepository<OfferWorkType, Long> {

    List<OfferWorkType> findAllByOfferId(Long offerId);

}
