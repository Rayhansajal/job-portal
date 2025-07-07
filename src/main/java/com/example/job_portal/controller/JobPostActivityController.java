package com.example.job_portal.controller;

import com.example.job_portal.dto.RecruiterJobsDto;
import com.example.job_portal.entity.IRecruiterJobs;
import com.example.job_portal.entity.JobPostActivity;
import com.example.job_portal.entity.RecruiterProfile;
import com.example.job_portal.entity.Users;
import com.example.job_portal.repository.JobPostActivityRepository;
import com.example.job_portal.service.JobPostActivityService;
import com.example.job_portal.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class JobPostActivityController {
    private final UsersService usersService;
    private final JobPostActivityService jobPostActivityService;
    private final JobPostActivityRepository jobPostActivityRepository;

    @GetMapping("/dashboard/")
    public String searchJob(Model model) {
        System.out.println("Dashboard controller called");
        try {
            Object currentUserProfile = usersService.getCurrentUserProfile();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Authenticated user: " + authentication.getName());

            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUsername = authentication.getName();
                model.addAttribute("username", currentUsername);
                System.out.println("Username added to model: " + currentUsername);

                if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
                    if (currentUserProfile instanceof RecruiterProfile) {
                        Long recruiterId = ((RecruiterProfile) currentUserProfile).getUserAccountId();
                        System.out.println("Recruiter ID: " + recruiterId);
                        List<RecruiterJobsDto> recruiterJobs = jobPostActivityService.getRecruiterJobs(recruiterId);
                        System.out.println("Jobs fetched: " + recruiterJobs.size());
                        model.addAttribute("jobPost", recruiterJobs);
                    }
                }
            }
            model.addAttribute("user", currentUserProfile);
            return "dashboard";

        } catch (Exception e) {
            e.printStackTrace();
            return "dashboard";
        }
    }



    @GetMapping("/dashboard/add")
    public String addJob(Model model) {
        model.addAttribute("jobPostActivity",new JobPostActivity());
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "add-jobs";
    }

    @PostMapping("/dashboard/addNew")
    public String addNew(JobPostActivity jobPostActivity,Model model ) {
    Users user= usersService.getCurrentUser();

   if(user!=null){
       jobPostActivity.setPostedById(user);
   }
   jobPostActivity.setJobPostedDate(new Date());
   model.addAttribute("jobPostActivity",jobPostActivity);
   JobPostActivity saved =jobPostActivityService.addNew(jobPostActivity);
        return "redirect:/dashboard/";
    }


    @GetMapping("/test-recruiter-jobs")
    @ResponseBody
    public List<IRecruiterJobs> testRecruiterJobs() {
        Long sampleRecruiterId = 1L;  // Use an existing recruiter ID
        List<IRecruiterJobs> jobs = jobPostActivityRepository.getRecruiterJobs(sampleRecruiterId);
        System.out.println("Recruiter jobs size: " + jobs.size());
        return jobs;
    }



}
