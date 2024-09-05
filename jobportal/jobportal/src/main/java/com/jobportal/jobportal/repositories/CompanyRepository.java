package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
