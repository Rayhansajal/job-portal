package com.example.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "job_seeker")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class JobSeekerProfile {
    @Id
    private Long userAccountId;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    @MapsId
    private Users usersId;

    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String workAuthorization;
    private String employmentType;
    private String resume;
    @Column(nullable = true,length = 64)
    private String profilePhoto;

    @OneToMany(targetEntity = Skills.class,cascade = CascadeType.ALL,mappedBy = "jobSeekerProfile")
    private List<Skills> skills;


    public JobSeekerProfile(Users users) {
        this.usersId = users;
    }
}
