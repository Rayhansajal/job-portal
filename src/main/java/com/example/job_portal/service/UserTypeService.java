package com.example.job_portal.service;

import com.example.job_portal.entity.UserType;

import java.util.List;

public interface UserTypeService {

    List<UserType> getAll();
    UserType save(UserType userType);
}
