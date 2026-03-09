package com.hannah.applyflow.job.controller;

import com.hannah.applyflow.global.security.CustomUserDetails;
import com.hannah.applyflow.job.dto.JobCreateRequest;
import com.hannah.applyflow.job.dto.JobResponse;
import com.hannah.applyflow.job.dto.JobUpdateRequest;
import com.hannah.applyflow.job.service.JobService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public JobResponse createJob(@RequestBody JobCreateRequest request) {
        return jobService.createJob(request);
    }

    @GetMapping
    public Page<JobResponse> getJobs(@AuthenticationPrincipal CustomUserDetails userDetails, Pageable pageable) {
        return jobService.getJobs(userDetails.getUser(), pageable);
    }

    @GetMapping("/{id}")
    public JobResponse getJob(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @PatchMapping("/{id}")
    public JobResponse updateJob(@PathVariable Long id, @RequestBody JobUpdateRequest request) {
        return jobService.updateJob(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}
