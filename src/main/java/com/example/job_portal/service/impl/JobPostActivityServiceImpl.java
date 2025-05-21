package com.example.job_portal.service.impl;

import com.example.job_portal.entity.JobPostActivity;
import com.example.job_portal.repository.JobPostActivityRepository;
import com.example.job_portal.service.JobPostActivityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobPostActivityServiceImpl implements JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;
    @Override
    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }
}
