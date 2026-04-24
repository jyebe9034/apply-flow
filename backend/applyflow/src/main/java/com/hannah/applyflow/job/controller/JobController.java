package com.hannah.applyflow.job.controller;

import com.hannah.applyflow.global.response.ApiResponse;
import com.hannah.applyflow.global.security.CustomUserDetails;
import com.hannah.applyflow.job.dto.JobCreateRequest;
import com.hannah.applyflow.job.dto.JobResponse;
import com.hannah.applyflow.job.dto.JobUpdateRequest;
import com.hannah.applyflow.job.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Job Application API", description = "Endpoints for managing job applications and tracking recruitment progress")
@Slf4j
@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @Operation(summary = "Create a new job application",
            description = "Registers a new job application with details like company name and application date.")
    @PostMapping
    public ResponseEntity<ApiResponse<JobResponse>> createJob(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody JobCreateRequest request) {
        JobResponse data = jobService.createJob(userDetails.getUser(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Job created successfully.", data));
    }

    @Operation(summary = "Get all job applications",
            description = "Retrieves a paginated list of job applications for the currently authenticated user.")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<JobResponse>>> getJobs(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Pageable pageable) {
        Page<JobResponse> data = jobService.getJobs(userDetails.getUser(), pageable);
        return ResponseEntity.ok(ApiResponse.success("Jobs retrieved successfully", data));
    }

    @Operation(summary = "Get job application details",
            description = "Retrieves the full details of a specific job application by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobResponse>> getJob(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        JobResponse data = jobService.getJobById(id, userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.success("Job retrieved successfully", data));
    }

    @Operation(summary = "Update job application",
            description = "Updates the information of an existing job application, such as status or interview dates.")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<JobResponse>> updateJob(
            @PathVariable Long id,
            @RequestBody JobUpdateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        JobResponse data = jobService.updateJob(id, request, userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.success("Job updated successfully", data));
    }

    @Operation(summary = "Delete job application",
            description = "Removes a job application from the user's history.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteJob(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        jobService.deleteJob(id, userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.success("Job deleted successfully."));
    }
}
