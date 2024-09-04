package com.jobportal.jobportal.entities.user;

import com.jobportal.jobportal.entities.Payment;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company_user")
@Data
@DiscriminatorValue("COMPANY")
public class Company extends User {

    @Length(max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();
}
