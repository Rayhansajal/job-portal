package com.example.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JobPostActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long JobPostId;

    @ManyToOne
    @JoinColumn(name = "postedById",referencedColumnName = "userId")
    private Users postedById;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobLocationId",referencedColumnName = "Id")
    private JobLocation jobLocationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobCompany",referencedColumnName = "Id")
    private JobCompany jobCompanyId;

    @Transient
    private boolean isActive;
  @Transient
    private boolean isSaved;

  @Length(max = 10000)
  private String descriptionOfJob;

  private String salary;
  private String jobType;
  private String remote;

  @DateTimeFormat(pattern = "DD-MM-YYYY")
  private Date jobPostedDate;
  private String jobTitle;


}
