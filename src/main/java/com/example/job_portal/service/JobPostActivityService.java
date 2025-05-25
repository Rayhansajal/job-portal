package com.example.job_portal.service;

import com.example.job_portal.dto.RecruiterJobsDto;
import com.example.job_portal.entity.JobPostActivity;

import java.util.List;

public interface JobPostActivityService {
    JobPostActivity addNew(JobPostActivity jobPostActivity);
    List<RecruiterJobsDto> getRecruiterJobs(Long recruiter);
}
