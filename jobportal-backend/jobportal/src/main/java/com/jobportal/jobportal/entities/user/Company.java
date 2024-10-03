package com.jobportal.jobportal.entities.user;

import com.jobportal.jobportal.entities.Payment;
import com.jobportal.jobportal.entities.offer.Offer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("COMPANY")
public class Company extends User {

    @Length(max = 100)
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^\\d{10}$", message = "NIP must be 10 digits long")
    @Column(name = "nip", unique = true)
    private String nip;

    @Min(1)
    @Column(name = "company_size")
    private Long companySize;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Offer> offers = new HashSet<>();
}
