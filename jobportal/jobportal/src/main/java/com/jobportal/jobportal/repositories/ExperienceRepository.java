package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.offer.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
