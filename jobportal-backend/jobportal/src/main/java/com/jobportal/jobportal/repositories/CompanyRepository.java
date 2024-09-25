package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.dtos.company.CompanyResponseOfferStatsDTO;
import com.jobportal.jobportal.entities.user.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT new com.jobportal.jobportal.dtos.company.CompanyResponseOfferStatsDTO(" +
            "c.name, COUNT(o), AVG(o.salaryMax)) " +
            "FROM Company c LEFT JOIN c.offers o " +
            "GROUP BY c.name " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'count' THEN COUNT(o) END DESC, " +
            "CASE WHEN :sortBy = 'average' THEN AVG(o.salaryMax) END DESC")
    List<CompanyResponseOfferStatsDTO> findCompaniesWithOfferStats(@Param("sortBy") String sortBy, Pageable pageable);

    Optional<Company> findByEmail(String email);
}
