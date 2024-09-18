package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userAuthority ua LEFT JOIN FETCH ua.authority WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
