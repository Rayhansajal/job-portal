package com.example.job_portal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private boolean isActive;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date registrationDate;

    @JoinColumn(name = "userTypeId",referencedColumnName = "userTypeId")
    @ManyToOne(cascade = CascadeType.ALL)
    private UserType userTypeId;
}
