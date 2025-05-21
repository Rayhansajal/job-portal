package com.example.job_portal.service.impl;

import com.example.job_portal.entity.UserType;
import com.example.job_portal.repository.UserTypeRepository;
import com.example.job_portal.service.UserTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;



    @Override
    public List<UserType> getAll() {
        return userTypeRepository.findAll();
    }

    @Override
    public UserType save(UserType userType) {
        return userTypeRepository.save(userType);
    }
}
