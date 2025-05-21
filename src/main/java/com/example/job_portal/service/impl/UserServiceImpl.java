package com.example.job_portal.service.impl;

import com.example.job_portal.entity.JobSeekerProfile;
import com.example.job_portal.entity.RecruiterProfile;
import com.example.job_portal.entity.Users;
import com.example.job_portal.repository.JobSeekerProfileRepository;
import com.example.job_portal.repository.RecruiterProfileRepository;
import com.example.job_portal.repository.UserRepository;
import com.example.job_portal.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final PasswordEncoder passwordEncoder;


    public Users addNewUser(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUser = userRepository.save(users);
        Long userTypeId = users.getUserTypeId().getUserTypeId();

        if (userTypeId == 1){
             recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;

    }

    @Override
    public Optional<Users> getByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    @Override
    public Object getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users users = userRepository.findAllByEmail(username).orElseThrow(() -> new UsernameNotFoundException("could not find user"));
            Long userId = users.getUserId();
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
                RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
                return recruiterProfile;
            }else {
                JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
                return jobSeekerProfile;

            }
        }
        return null;

    }

    @Override
    public Users getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof  AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users user = userRepository.findAllByEmail(username).orElseThrow(() -> new UsernameNotFoundException("could not find user"));
            return user;
        }
        return null;
    }
}
