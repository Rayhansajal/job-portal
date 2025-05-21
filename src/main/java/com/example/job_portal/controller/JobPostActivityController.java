package com.example.job_portal.controller;

import com.example.job_portal.entity.JobPostActivity;
import com.example.job_portal.entity.Users;
import com.example.job_portal.service.JobPostActivityService;
import com.example.job_portal.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
@AllArgsConstructor
public class JobPostActivityController {
    private final UsersService usersService;
    private final JobPostActivityService jobPostActivityService;

    @GetMapping("/dashboard/")
    public String SearchJob(Model model) {
        Object currentUserProfile = usersService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            model.addAttribute("username" ,currentUsername);
        }
        model.addAttribute("user" , currentUserProfile);
        System.out.println("dashboard");
        return "dashboard";

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
   jobPostActivity.setJobPosteDate(new Date());
   model.addAttribute("jobPostActivity",jobPostActivity);
   JobPostActivity saved =jobPostActivityService.addNew(jobPostActivity);
        return "redirect:/dashboard/";
    }


}
