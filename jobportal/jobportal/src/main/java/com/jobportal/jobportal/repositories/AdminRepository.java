package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
