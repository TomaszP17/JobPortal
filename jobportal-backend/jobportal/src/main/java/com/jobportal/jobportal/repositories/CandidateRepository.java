package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.user.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
