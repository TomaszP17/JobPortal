package com.jobportal.jobportal.entities;

import com.jobportal.jobportal.entities.offer.Offer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "localization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @OneToMany(mappedBy = "localization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Offer> offers = new HashSet<>();
}
