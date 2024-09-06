package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.UserFavouriteOffer;
import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavouriteOfferRepository extends JpaRepository<UserFavouriteOffer, Long> {

    @Query("SELECT ufo.offer.id FROM UserFavouriteOffer ufo WHERE ufo.user.id = :userId")
    List<Long> findAllOfferIdsByUserId(@Param("userId") Long userId);

    UserFavouriteOffer findByUserAndOffer(User user, Offer offer);

}
