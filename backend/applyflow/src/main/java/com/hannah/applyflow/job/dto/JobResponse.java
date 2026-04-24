package com.hannah.applyflow.job.dto;

import com.hannah.applyflow.job.Job;
import com.hannah.applyflow.job.JobPlatform;
import com.hannah.applyflow.job.JobStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Job application detail information")
@Getter
@Builder
public class JobResponse {

    @Schema(description = "Unique identifier of the job application", example = "1")
    private Long id;

    @Schema(description = "Name of the company", example = "Adidas AG")
    private String companyName;

    @Schema(description = "Applied position", example = "Full-stack Developer")
    private String position;

    @Schema(description = "Current application status (e.g., APPLIED, DOCUMENT_PASSED, INTERVIEW, OFFER, REJECTED)", example = "INTERVIEW")
    private JobStatus status;

    @Schema(description = "The date the application was submitted", example = "2026-04-20")
    private LocalDate appliedAt;

    @Schema(description = "URL of the job posting", example = "https://www.xing.com/jobs/12345")
    private String jobUrl;

    @Schema(description = "Annual gross salary in EUR", example = "62000")
    private Long salary;

    @Schema(description = "City or region of the office", example = "Berlin")
    private String location;

    @Schema(description = "Sourcing platform of the job", example = "XING")
    private JobPlatform platform;

    @Schema(description = "Contact person's name", example = "Sarah Schmidt")
    private String contactName;

    @Schema(description = "Contact person's email", example = "recruitment@adidas.de")
    private String contactEmail;

    @Schema(description = "Contact person's phone number", example = "+49 9132 840")
    private String contactPhone;

    @Schema(description = "Scheduled interview date and time", example = "2026-05-15T10:30:00")
    private LocalDateTime interviewDateTime;

    @Schema(description = "Personal notes regarding the application", example = "The second round is a live coding test.")
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
