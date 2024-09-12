package com.jobportal.jobportal.entities.offer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "offer_employment_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
