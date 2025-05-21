package com.example.job_portal.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        System.out.println("User logged in: " + username);

        boolean hasJobSeekerRole = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("job_seeker"));
        boolean hasRecruiterRole = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("recruiter"));

        if (hasRecruiterRole || hasJobSeekerRole) {
            System.out.println("Redirecting to /dashboard/");
            response.sendRedirect("/dashboard/");
        } else {
            System.out.println("No recognized role found, redirecting to /login?error=role");
            response.sendRedirect("/login?error=role");
        }
    }
}
