package com.jobportal.jobportal.entity.offer;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employment_type")
@Data
public class EmploymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "employmentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OfferEmploymentType> offerEmploymentTypes = new HashSet<>();
}
