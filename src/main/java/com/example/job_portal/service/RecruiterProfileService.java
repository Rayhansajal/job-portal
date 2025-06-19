package com.example.job_portal.service;

import com.example.job_portal.entity.RecruiterProfile;

import java.util.Optional;

public interface RecruiterProfileService {
    Optional<RecruiterProfile> getOne(Long id);

    RecruiterProfile addNew(RecruiterProfile recruiterProfile);
}
