package com.hannah.applyflow.job.dto;

import com.hannah.applyflow.job.JobPlatform;
import com.hannah.applyflow.job.JobStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "Request model for updating an existing job application record")
@Getter
@Builder
@AllArgsConstructor
public class JobUpdateRequest {

    @Schema(description = "Updated job position", example = "Senior Backend Developer")
    private String position;

    @Schema(description = "Updated application status", example = "REJECTED")
    private JobStatus status;

    @Schema(description = "Updated link to the job posting", example = "https://www.stepstone.de/jobs/12345")
    private String jobUrl;

    @Schema(description = "Revised annual gross salary (EUR)", example = "72000")
    private Long salary;

    @Schema(description = "Updated office location", example = "Hamburg")
    private String location;

    @Schema(description = "Updated platform where the job was found", example = "STEPSTONE")
    private JobPlatform platform;

    @Schema(description = "Updated name of the contact person", example = "Lukas Schmidt")
    private String contactName;

    @Schema(description = "Updated contact email address", example = "career@company.de")
    private String contactEmail;

    @Schema(description = "Updated contact phone number", example = "+49 40 1234567")
    private String contactPhone;

    @Schema(description = "Updated interview date and time", example = "2026-05-20T10:00:00")
    private LocalDateTime interviewDateTime;

    @Schema(description = "New notes or updates regarding the application", example = "Received a positive feedback after the second round.")
    private String memo;

}
