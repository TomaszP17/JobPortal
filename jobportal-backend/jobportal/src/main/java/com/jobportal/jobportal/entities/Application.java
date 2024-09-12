package com.jobportal.jobportal.entities;

import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.entities.user.Candidate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

    @Length(max = 100)
    @Column(name = "first_name")
    private String firstName;

    @Length(max = 100)
    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email", length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "candidate_user_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "application_status", nullable = false)
    private ApplicationStatus applicationStatus;

    @OneToOne
    @JoinColumn(name = "pdf_id", referencedColumnName = "id")
    private Pdf pdf;

}
