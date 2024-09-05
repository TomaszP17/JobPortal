package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.UserFavouriteOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavouriteOfferRepository extends JpaRepository<UserFavouriteOffer, Long> {
}
