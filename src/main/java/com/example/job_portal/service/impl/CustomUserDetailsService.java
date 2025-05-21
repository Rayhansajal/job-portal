package com.example.job_portal.service.impl;

import com.example.job_portal.entity.Users;
import com.example.job_portal.repository.UserRepository;
import com.example.job_portal.util.CustomerUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findAllByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new CustomerUserDetails(users);
    }
}
