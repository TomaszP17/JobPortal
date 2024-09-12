package com.jobportal.jobportal.entities.offer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "offer_technology")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OfferTechnology {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "technology_id", nullable = false)
    private Technology technology;

}
