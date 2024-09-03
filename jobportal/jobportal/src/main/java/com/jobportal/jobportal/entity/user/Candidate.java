package com.jobportal.jobportal.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "company")
@Data
public class Candidate extends User {

    @Length(max = 100)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Length(max = 100)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "experience_years", nullable = false)
    private Integer experienceYears = 0;

    @Lob
    @Column(name = "pdf")
    private byte[] pdf;
}
