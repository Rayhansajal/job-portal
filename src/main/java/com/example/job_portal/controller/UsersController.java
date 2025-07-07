package com.example.job_portal.controller;

import com.example.job_portal.entity.UserType;
import com.example.job_portal.entity.Users;
import com.example.job_portal.service.UserTypeService;
import com.example.job_portal.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UserTypeService userTypeService;
    private final UsersService usersService;

    @GetMapping("/register")
    public String register(Model model){
        List<UserType> userTypes = userTypeService.getAll();
        model.addAttribute("getAllTypes",userTypes);
        model.addAttribute("user",new Users());
        return "register";

    }

    @PostMapping("/register/new")
    public String UserRegister(@Valid Users users ,Model model){


        Optional<Users> emil = usersService.getByEmail(users.getEmail());
        if(emil.isPresent()){
            model.addAttribute("error","This email already exists");
            List<UserType> userTypes = userTypeService.getAll();
            model.addAttribute("getAllTypes",userTypes);
            model.addAttribute("user",new Users());
            return "register";
        }

        usersService.addNewUser(users);
        return "/login";


    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);

        }
        return "redirect:/";

    }
}
