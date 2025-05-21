package com.example.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recruiter")
@Getter
@Setter
@ToString
public class RecruiterProfile {
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
    @Column(nullable = true,length = 64)
    private String profilePhoto;

    public RecruiterProfile(Users users) {
        this.usersId = users;
    }
}
