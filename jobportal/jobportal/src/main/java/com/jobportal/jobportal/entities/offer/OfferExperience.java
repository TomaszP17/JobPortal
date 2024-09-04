package com.jobportal.jobportal.entities.offer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "offer_experience")
@Data
public class OfferExperience {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "experience_id", nullable = false)
    private Experience experience;
}
