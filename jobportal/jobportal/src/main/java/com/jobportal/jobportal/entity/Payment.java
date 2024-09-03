package com.jobportal.jobportal.entity;

import com.jobportal.jobportal.entity.offer.Offer;
import com.jobportal.jobportal.entity.user.Company;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "company_user_id", nullable = false)
    private Company company;

    @Column(name = "amount", precision = 7, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;

}
