package com.jobportal.jobportal.entities.offer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "offer_employment_type")
@Data
public class OfferEmploymentType {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "employment_type_id", nullable = false)
    private EmploymentType employmentType;
}
