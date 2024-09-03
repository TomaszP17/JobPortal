package com.jobportal.jobportal.entity.offer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "offer_work_type")
@Data
public class OfferWorkType {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "work_type_id", nullable = false)
    private WorkType workType;
}
