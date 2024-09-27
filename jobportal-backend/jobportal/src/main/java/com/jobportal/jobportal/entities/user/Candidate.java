package com.jobportal.jobportal.entities.user;

import com.jobportal.jobportal.entities.Pdf;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "candidate_user")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CANDIDATE")
public class Candidate extends User {

    @Length(max = 100)
    @Column(name = "first_name")
    private String firstName;

    @Length(max = 100)
    @Column(name = "last_name")
    private String lastName;

    @Builder.Default
    @Column(name = "experience_years", nullable = false)
    private Integer experienceYears = 0;

    @OneToOne
    @JoinColumn(name = "pdf_id", referencedColumnName = "id")
    private Pdf pdf;
}
