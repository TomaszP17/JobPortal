package com.jobportal.jobportal.entities;

import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.entities.user.Candidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_user_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @Lob
    @Column(name = "pdf")
    private byte[] pdf;

    @ManyToOne
    @JoinColumn(name = "application_status", nullable = false)
    private ApplicationStatus applicationStatus;

}
