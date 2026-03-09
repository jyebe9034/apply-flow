package com.hannah.applyflow.job.dto;

import com.hannah.applyflow.job.JobPlatform;
import com.hannah.applyflow.job.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class JobUpdateRequest {

    private String position;
    private JobStatus status;
    private String jobUrl;
    private Long salary;
    private String location;
    private JobPlatform platform;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private LocalDateTime interviewDateTime;
    private String memo;
}
