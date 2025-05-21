package com.example.job_portal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_type")
public class UserType {
    @Id
    private Long userTypeId;

    private String userTypeName;
    @OneToMany(targetEntity = Users.class,mappedBy = "userTypeId",cascade = CascadeType.ALL)
    private List<Users> users;


}
