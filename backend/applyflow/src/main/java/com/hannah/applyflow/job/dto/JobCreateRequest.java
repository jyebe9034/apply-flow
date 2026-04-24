package com.hannah.applyflow.job.dto;

import com.hannah.applyflow.job.JobPlatform;
import com.hannah.applyflow.job.JobStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Detailed information of a job application record")
@Getter
@Builder
@AllArgsConstructor
public class JobCreateRequest {

    @Schema(description = "Name of the company", example = "SAP SE")
    private String companyName;

    @Schema(description = "Job title/position", example = "Software Engineer")
    private String position;

    @Schema(description = "Current status of the application", implementation = JobStatus.class)
    private JobStatus status;

    @Schema(description = "The date when the application was submitted", example = "2026-04-23")
    private LocalDate appliedAt;

    @Schema(description = "Link to the job posting", example = "https://www.linkedin.com/jobs/view/12345")
    private String jobUrl;

    @Schema(description = "Expected or offered annual gross salary (EUR)", example = "65000")
    private Long salary;

    @Schema(description = "Office location or city", example = "Frankfurt am Main")
    private String location;

    @Schema(description = "Platform where the job was found", implementation = JobPlatform.class)
    private JobPlatform platform;

    @Schema(description = "Name of the recruiter or contact person", example = "Thomas Müller")
    private String contactName;

    @Schema(description = "Email address of the contact person", example = "hr@company.de")
    private String contactEmail;

    @Schema(description = "Phone number of the contact person", example = "+49 123 4567890")
    private String contactPhone;

    @Schema(description = "Scheduled interview date and time (ISO 8601)", example = "2026-05-10T14:00:00")
    private LocalDateTime interviewDateTime;

    @Schema(description = "Additional notes or remarks", example = "First technical interview with the CTO.")
    private String memo;

}
