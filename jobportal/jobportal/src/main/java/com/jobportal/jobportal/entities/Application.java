package com.jobportal.jobportal.entities;

import com.jobportal.jobportal.entities.user.Candidate;
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
