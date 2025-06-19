package com.example.job_portal.dto;

import com.example.job_portal.entity.JobCompany;
import com.example.job_portal.entity.JobLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class  RecruiterJobsDto {
        private Long totalCandidates;
        private Long jobPostId;
        private String jobTitle;
        private JobLocation jobLocationId;
        private JobCompany jobCompanyId;


    }
