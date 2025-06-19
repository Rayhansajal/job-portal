package com.example.job_portal.service.impl;

import com.example.job_portal.entity.RecruiterProfile;
import com.example.job_portal.repository.RecruiterProfileRepository;
import com.example.job_portal.service.RecruiterProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RecruiterProfileServiceImpl implements RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    @Override
    public Optional<RecruiterProfile> getOne(Long id) {
        return recruiterProfileRepository.findById(id);
    }

    @Override
    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }
}
