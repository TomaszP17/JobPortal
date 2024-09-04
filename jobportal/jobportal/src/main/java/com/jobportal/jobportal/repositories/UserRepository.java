package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
