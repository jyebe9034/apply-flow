package com.hannah.applyflow.dashboard.dto;

import com.hannah.applyflow.job.JobStatus;
import com.hannah.applyflow.job.dto.JobResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class DashboardResponse {
    private long totalCount;
    private Map<JobStatus, Long> statusCount;
    private List<JobResponse> recentJobs;
    private List<JobResponse> upcomingInterviews;
}