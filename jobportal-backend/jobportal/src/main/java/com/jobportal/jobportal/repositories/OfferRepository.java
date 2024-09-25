package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.dtos.offer.SimilarOfferResponseDTO;
import com.jobportal.jobportal.entities.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(value = "SELECT id, title, expiry_date, salary_min, salary_max, description, similarity_score " +
            "FROM get_similar_offers_with_details(:offerId, :offerCount)", nativeQuery = true)
    List<Object[]> getSimilarOffersWithDetails(@Param("offerId") long offerId, @Param("offerCount") int offerCount);
}
