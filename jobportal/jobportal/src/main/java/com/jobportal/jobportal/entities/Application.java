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
    @JoinColumn(name = "candidate_user_id", nullable = true)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @Lob
    @Column(name = "pdf", nullable = true)
    private byte[] pdf;

}
