package com.hannah.applyflow.job.dto;

import com.hannah.applyflow.job.Job;
import com.hannah.applyflow.job.JobPlatform;
import com.hannah.applyflow.job.JobStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class JobResponse {

    private Long id;
    private String companyName;
    private String position;
    private JobStatus status;
    private LocalDate appliedAt;
    private String jobUrl;
    private Long salary;
    private String location;
    private JobPlatform platform;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private LocalDateTime interviewDateTime;
    private String memo;

    public JobResponse(Job job) {
        this.id = job.getId();
        this.companyName = job.getCompanyName();
        this.position = job.getPosition();
        this.status = job.getStatus();
        this.appliedAt = job.getAppliedAt();
        this.jobUrl = job.getJobUrl();
        this.salary = job.getSalary();
        this.location = job.getLocation();
        this.platform = job.getPlatform();
        this.contactName = job.getContactName();
        this.contactEmail = job.getContactEmail();
        this.contactPhone = job.getContactPhone();
        this.interviewDateTime = job.getInterviewDateTime();
        this.memo = job.getMemo();
    }
}
