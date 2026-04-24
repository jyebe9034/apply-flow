package com.hannah.applyflow.dashboard.dto;

import com.hannah.applyflow.job.JobStatus;
import com.hannah.applyflow.job.dto.JobResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
import java.util.Map;

@Schema(description = "Aggregated data for the recruitment dashboard, providing a snapshot of the user's job search progress")
@Getter
@Builder
public class DashboardResponse {

    @Schema(description = "Total number of job applications registered by the user", example = "42")
    private long totalCount;

    @Schema(description = "Count of applications categorized by their current status",
            example = "{ \"APPLIED\": 15, \"INTERVIEW\": 4, \"OFFER\": 2, \"REJECTED\": 21 }")
    private Map<JobStatus, Long> statusCount;

    @Schema(description = "List of the most recently added job applications to track latest activity")
    private List<JobResponse> recentJobs;

    @Schema(description = "List of scheduled interviews, prioritized by the nearest upcoming date")
    private List<JobResponse> upcomingInterviews;

    public static DashboardResponse of(long totalCount, Map<JobStatus, Long> statusCount,
                                       List<JobResponse> recentJobs, List<JobResponse> upcomingInterviews) {
        return DashboardResponse.builder()
                .totalCount(totalCount)
                .statusCount(statusCount)
                .recentJobs(recentJobs)
                .upcomingInterviews(upcomingInterviews)
                .build();
    }
}