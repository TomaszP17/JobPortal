package com.jobportal.jobportal.entity;

import com.jobportal.jobportal.entity.user.Candidate;
import com.jobportal.jobportal.entity.user.Company;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "application")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_user_id", nullable = false)
    private Candidate candidate;
}
