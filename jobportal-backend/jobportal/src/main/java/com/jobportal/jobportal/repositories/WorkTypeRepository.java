package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.offer.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {
}
