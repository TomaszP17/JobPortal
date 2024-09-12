package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Long> {
}
