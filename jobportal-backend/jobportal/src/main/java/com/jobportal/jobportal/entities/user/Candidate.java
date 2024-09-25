package com.jobportal.jobportal.entities.user;

import com.jobportal.jobportal.entities.Pdf;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "candidate_user")
@Data
@DiscriminatorValue("CANDIDATE")
public class Candidate extends User {

    @Length(max = 100)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Length(max = 100)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "experience_years", nullable = false)
    private Integer experienceYears = 0;

    @OneToOne
    @JoinColumn(name = "pdf_id", referencedColumnName = "id")
    private Pdf pdf;
}
