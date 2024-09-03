package com.jobportal.jobportal.entity;

import com.jobportal.jobportal.entity.offer.Offer;
import com.jobportal.jobportal.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_favourite_offer")
@Data
public class UserFavouriteOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;
}
