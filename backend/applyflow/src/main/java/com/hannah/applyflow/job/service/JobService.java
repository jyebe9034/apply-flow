package com.hannah.applyflow.job.service;

import com.hannah.applyflow.global.exception.JobNotFoundException;
import com.hannah.applyflow.job.Job;
import com.hannah.applyflow.job.JobStatus;
import com.hannah.applyflow.job.dto.JobCreateRequest;
import com.hannah.applyflow.job.dto.JobResponse;
import com.hannah.applyflow.job.dto.JobUpdateRequest;
import com.hannah.applyflow.job.repository.JobRepository;
import com.hannah.applyflow.user.User;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public JobResponse createJob(JobCreateRequest request) {
        Job job = Job.builder()
                .companyName(request.getCompanyName())
                .position(request.getPosition())
                .status(JobStatus.APPLIED)
                .appliedAt(LocalDate.now())
                .jobUrl(request.getJobUrl())
                .salary(request.getSalary())
                .location(request.getLocation())
                .platform(request.getPlatform())
                .contactName(request.getContactName())
                .contactEmail(request.getContactEmail())
                .contactPhone(request.getContactPhone())
                .build();
        jobRepository.save(job);
        return new JobResponse(job);
    }

    @Transactional(readOnly = true)
    public Page<JobResponse> getJobs(User user, Pageable pageable) {
        return jobRepository.findAllByUser(user, pageable).map(JobResponse::new);
    }

    @Transactional(readOnly = true)
    public JobResponse getJobById(Long id) {
        Job job = findJobById(id);
        return new JobResponse(job);
    }

    public JobResponse updateJob(Long id, JobUpdateRequest request) {
        Job job = findJobById(id);
        job.update(request);
        return new JobResponse(job);
    }

    public void deleteJob(Long id) {
        Job job = findJobById(id);
        jobRepository.delete(job);
    }

    private Job findJobById(Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException(id));
    }
}
