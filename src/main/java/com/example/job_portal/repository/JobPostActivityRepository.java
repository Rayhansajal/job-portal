package com.example.job_portal.repository;

import com.example.job_portal.entity.IRecruiterJobs;
import com.example.job_portal.entity.JobPostActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobPostActivityRepository extends JpaRepository<JobPostActivity, Long> {
    @Query(value = " SELECT COUNT(s.user_id) as totalCandidates, j.job_post_id as jobPostId, j.job_title as jobTitle, l.id as locationId, l.city, l.state, l.country, c.id as companyId, c.name " +
            " FROM job_post_activity j " +
            " INNER JOIN job_location l ON j.job_location_id = l.id " +
            " INNER JOIN job_company c ON j.job_company_id = c.id " +
            " LEFT JOIN job_seeker_apply s ON s.job = j.job_post_id " +
            " WHERE j.posted_by_id = :recruiter " +
            " GROUP BY j.job_post_id", nativeQuery = true)
    List<IRecruiterJobs> getRecruiterJobs(@Param("recruiter") Long recruiter);
}
