package com.hannah.applyflow.job.service;

import com.hannah.applyflow.global.exception.CustomException;
import com.hannah.applyflow.global.response.ErrorCode;
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

    public JobResponse createJob(User user, JobCreateRequest request) {
        Job job = Job.builder()
                .user(user)
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
        return JobResponse.from(job);
    }

    @Transactional(readOnly = true)
    public Page<JobResponse> getJobs(User user, Pageable pageable) {
        return jobRepository.findAllByUser(user, pageable).map(JobResponse::new);
    }

    @Transactional(readOnly = true)
    public JobResponse getJobById(Long id, User currentUser) {
        Job job = findJobById(id);
        validateJobOwner(job, currentUser);
        return JobResponse.from(job);
    }

    public JobResponse updateJob(Long id, JobUpdateRequest request, User currentUser) {
        Job job = findJobById(id);
        validateJobOwner(job, currentUser);

        job.update(request);
        return JobResponse.from(job);
    }

    public void deleteJob(Long id, User currentUser) {
        Job job = findJobById(id);
        validateJobOwner(job, currentUser);

        jobRepository.delete(job);
    }

    private Job findJobById(Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
    }

    private void validateJobOwner(Job job, User currentUser) {
        if (!job.getUser().getId().equals(currentUser.getId())) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }
    }
}
