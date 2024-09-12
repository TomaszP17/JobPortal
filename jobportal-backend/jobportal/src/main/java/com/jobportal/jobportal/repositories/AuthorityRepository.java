package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.user.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
