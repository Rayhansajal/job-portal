package com.example.job_portal.repository;

import com.example.job_portal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findAllByEmail(String email);
}
