package com.hannah.applyflow.job.dto;

import com.hannah.applyflow.job.Job;
import com.hannah.applyflow.job.JobPlatform;
import com.hannah.applyflow.job.JobStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
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

    public static JobResponse from(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .companyName(job.getCompanyName())
                .position(job.getPosition())
                .status(job.getStatus())
                .appliedAt(job.getAppliedAt())
                .jobUrl(job.getJobUrl())
                .salary(job.getSalary())
                .location(job.getLocation())
                .platform(job.getPlatform())
                .contactName(job.getContactName())
                .contactEmail(job.getContactEmail())
                .contactPhone(job.getContactPhone())
                .interviewDateTime(job.getInterviewDateTime())
                .memo(job.getMemo())
                .build();
    }
}
