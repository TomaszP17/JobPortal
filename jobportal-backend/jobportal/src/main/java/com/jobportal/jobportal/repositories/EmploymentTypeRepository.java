package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.offer.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Long> {
}
