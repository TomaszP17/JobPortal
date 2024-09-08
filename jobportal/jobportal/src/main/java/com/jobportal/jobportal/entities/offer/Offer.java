package com.jobportal.jobportal.entities.offer;

import com.jobportal.jobportal.entities.Payment;
import com.jobportal.jobportal.entities.UserFavouriteOffer;
import com.jobportal.jobportal.entities.user.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "offer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Length(max = 50)
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "salary_min")
    @Min(0)
    private Integer salaryMin;

    @Column(name = "salary_max")
    @Min(0)
    private Integer salaryMax;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_paid")
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OfferTechnology> offerTechnologies = new HashSet<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OfferExperience> offerExperiences = new HashSet<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OfferEmploymentType> offerEmploymentTypes = new HashSet<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OfferWorkType> offerWorkTypes = new HashSet<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserFavouriteOffer> userFavouriteOffers = new HashSet<>();

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }
}
