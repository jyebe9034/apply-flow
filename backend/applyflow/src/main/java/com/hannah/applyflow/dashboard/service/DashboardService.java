package com.hannah.applyflow.dashboard.service;

import com.hannah.applyflow.dashboard.dto.DashboardResponse;
import com.hannah.applyflow.job.Job;
import com.hannah.applyflow.job.JobStatus;
import com.hannah.applyflow.job.dto.JobResponse;
import com.hannah.applyflow.job.repository.JobRepository;
import com.hannah.applyflow.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final JobRepository jobRepository;

    public DashboardResponse getDashboard(User user) {
        List<Job> allJobs = jobRepository.findAllByUser(user);

        long totalCount = allJobs.size();

        Map<JobStatus, Long> statusCount = allJobs.stream()
                .collect(Collectors.groupingBy(Job::getStatus, Collectors.counting()));

        List<JobResponse> recentJobs = allJobs.stream()
                .sorted(Comparator.comparing(Job::getAppliedAt).reversed())
                .limit(5)
                .map(JobResponse::from)
                .toList();

        List<JobResponse> upcomingInterviews = allJobs.stream()
                .filter(job -> job.getInterviewDateTime() != null
                        && job.getInterviewDateTime().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Job::getInterviewDateTime))
                .limit(5)
                .map(JobResponse::from)
                .toList();

        return new DashboardResponse(totalCount, statusCount, recentJobs, upcomingInterviews);
    }
}
