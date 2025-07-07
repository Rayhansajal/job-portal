package com.example.job_portal.controller;


import com.example.job_portal.entity.RecruiterProfile;
import com.example.job_portal.entity.Users;
import com.example.job_portal.repository.UserRepository;
import com.example.job_portal.service.RecruiterProfileService;
import com.example.job_portal.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {
    private final UserRepository userRepository;
    private final RecruiterProfileService recruiterProfileService;

    @GetMapping("/")
    public String recruiterProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = userRepository.findAllByEmail(currentUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not find user"));

            Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getOne(users.getUserId());
            if (recruiterProfile.isPresent()) {
                model.addAttribute("profile", recruiterProfile.get());
            } else {
                model.addAttribute("profile", new RecruiterProfile());
            }
        } else {
            model.addAttribute("profile", new RecruiterProfile());
        }
        return "recruiter-profile";
    }


    @PostMapping("/addNew")
    public String addNew (RecruiterProfile recruiterProfile, @RequestParam ("image")
                              MultipartFile multipartFile,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = userRepository.findAllByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not" + "find user"));
            recruiterProfile.setUsersId(users);
            recruiterProfile.setUserAccountId(users.getUserId());
        }
        model.addAttribute("profile", recruiterProfile);
        String filename= "";
        if(!multipartFile.getOriginalFilename().equals(" ")){
            filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            recruiterProfile.setProfilePhoto(filename);
        }
        RecruiterProfile savedUser = recruiterProfileService.addNew(recruiterProfile);
        String uploadDir = "photos/recruiter/"+savedUser.getUserAccountId();
        try {
            FileUploadUtil.saveFile(uploadDir,filename,multipartFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/dashboard/";
    }

}