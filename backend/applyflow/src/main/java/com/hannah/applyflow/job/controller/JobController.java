package com.hannah.applyflow.job.controller;

import com.hannah.applyflow.global.response.ApiResponse;
import com.hannah.applyflow.global.security.CustomUserDetails;
import com.hannah.applyflow.job.dto.JobCreateRequest;
import com.hannah.applyflow.job.dto.JobResponse;
import com.hannah.applyflow.job.dto.JobUpdateRequest;
import com.hannah.applyflow.job.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<ApiResponse<JobResponse>> createJob(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody JobCreateRequest request) {
        JobResponse data = jobService.createJob(userDetails.getUser(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Job created successfully.", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<JobResponse>>> getJobs(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Pageable pageable) {
        Page<JobResponse> data = jobService.getJobs(userDetails.getUser(), pageable);
        return ResponseEntity.ok(ApiResponse.success("Jobs retrieved successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobResponse>> getJob(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        JobResponse data = jobService.getJobById(id, userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.success("Job retrieved successfully", data));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<JobResponse>> updateJob(
            @PathVariable Long id,
            @RequestBody JobUpdateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        JobResponse data = jobService.updateJob(id, request, userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.success("Job updated successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteJob(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        jobService.deleteJob(id, userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.success("Job deleted successfully."));
    }
}
