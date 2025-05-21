package com.example.job_portal.service;

import ch.qos.logback.core.model.Model;
import com.example.job_portal.entity.JobPostActivity;
import com.example.job_portal.entity.Users;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UsersService {
    Users addNewUser(Users user);
    Optional<Users> getByEmail(String email);

    Object getCurrentUserProfile();



    Users getCurrentUser();
}
