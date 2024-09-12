package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByLocalization_Name(String name);


}
