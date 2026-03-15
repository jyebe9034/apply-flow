package com.hannah.applyflow.job.controller;

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
    public ResponseEntity<JobResponse> createJob(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody JobCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(userDetails.getUser(), request));
    }

    @GetMapping
    public ResponseEntity<Page<JobResponse>> getJobs(@AuthenticationPrincipal CustomUserDetails userDetails, Pageable pageable) {
        return ResponseEntity.ok(jobService.getJobs(userDetails.getUser(), pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJob(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(jobService.getJobById(id, userDetails.getUser()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Long id, @RequestBody JobUpdateRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(jobService.updateJob(id, request, userDetails.getUser()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        jobService.deleteJob(id, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
