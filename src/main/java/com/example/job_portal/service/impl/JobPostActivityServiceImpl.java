package com.example.job_portal.service.impl;

import com.example.job_portal.dto.RecruiterJobsDto;
import com.example.job_portal.entity.IRecruiterJobs;
import com.example.job_portal.entity.JobCompany;
import com.example.job_portal.entity.JobLocation;
import com.example.job_portal.entity.JobPostActivity;
import com.example.job_portal.repository.JobPostActivityRepository;
import com.example.job_portal.service.JobPostActivityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JobPostActivityServiceImpl implements JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;
    @Override
    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }

    @Override
    public List<RecruiterJobsDto> getRecruiterJobs(Long recruiter) {
        List<IRecruiterJobs> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiter);
        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();
        for (IRecruiterJobs rec : recruiterJobsDtos) {
            JobLocation loc= new JobLocation(rec.getLocationId(),rec.getCity(),rec.getState(),rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(),rec.getJobPostId(),rec.getJobTitle(),loc,comp));
        }
        return recruiterJobsDtoList;
    }


}
