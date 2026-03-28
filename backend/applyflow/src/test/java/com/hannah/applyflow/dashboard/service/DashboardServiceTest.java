package com.hannah.applyflow.dashboard.service;

import com.hannah.applyflow.dashboard.dto.DashboardResponse;
import com.hannah.applyflow.job.Job;
import com.hannah.applyflow.job.JobStatus;
import com.hannah.applyflow.job.repository.JobRepository;
import com.hannah.applyflow.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private DashboardService dashboardService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .email("hannah@test.com")
                .build();
    }

    // ==============totalCount==============

    @Test
    void getDashboard_totalCount() {
        // given
        List<Job> jobs = List.of(
                createJob("Google", JobStatus.APPLIED, LocalDate.now(), null),
                createJob("Meta", JobStatus.INTERVIEW, LocalDate.now(), null),
                createJob("Apple", JobStatus.REJECTED, LocalDate.now(), null)
        );
        given(jobRepository.findAllByUser(testUser)).willReturn(jobs);

        // when
        DashboardResponse response = dashboardService.getDashboard(testUser);

        // then
        assertThat(response.getTotalCount()).isEqualTo(3);
    }

    @Test
    void getDashboard_totalCount_empty() {
        // given
        given(jobRepository.findAllByUser(testUser)).willReturn(List.of());

        // when
        DashboardResponse response = dashboardService.getDashboard(testUser);

        // then
        assertThat(response.getTotalCount()).isEqualTo(0);
    }

    // ==============statusCount==============

    @Test
    void getDashboard_statusCount() {
        // given
        List<Job> jobs = List.of(
                createJob("Google", JobStatus.APPLIED, LocalDate.now(), null),
                createJob("Meta", JobStatus.APPLIED, LocalDate.now(), null),
                createJob("Apple", JobStatus.INTERVIEW, LocalDate.now(), null),
                createJob("Amazon", JobStatus.REJECTED, LocalDate.now(), null)
        );
        given(jobRepository.findAllByUser(testUser)).willReturn(jobs);

        // when
        DashboardResponse response = dashboardService.getDashboard(testUser);

        // then
        assertThat(response.getStatusCount().get(JobStatus.APPLIED)).isEqualTo(2L);
        assertThat(response.getStatusCount().get(JobStatus.INTERVIEW)).isEqualTo(1L);
        assertThat(response.getStatusCount().get(JobStatus.REJECTED)).isEqualTo(1L);
    }

    // ==============recentJob==============

    @Test
    void getDashboard_recentJobs_sortedByAppliedAtDesc() {
        // given
        List<Job> jobs = List.of(
                createJob("Google", JobStatus.APPLIED, LocalDate.now().minusDays(2), null),
                createJob("Meta", JobStatus.APPLIED, LocalDate.now(), null),
                createJob("Apple", JobStatus.APPLIED, LocalDate.now().minusDays(1), null)
        );
        given(jobRepository.findAllByUser(testUser)).willReturn(jobs);

        // when
        DashboardResponse response = dashboardService.getDashboard(testUser);

        // then
        assertThat(response.getRecentJobs().get(0).getCompanyName()).isEqualTo("Meta");
        assertThat(response.getRecentJobs().get(1).getCompanyName()).isEqualTo("Apple");
        assertThat(response.getRecentJobs().get(2).getCompanyName()).isEqualTo("Google");
    }

    @Test
    void getDashboard_recentJobs_limitFive() {
        // given - 6 jobs
        List<Job> jobs = List.of(
                createJob("Google", JobStatus.APPLIED, LocalDate.now(), null),
                createJob("Meta", JobStatus.APPLIED, LocalDate.now().minusDays(1), null),
                createJob("Apple", JobStatus.APPLIED, LocalDate.now().minusDays(2), null),
                createJob("Amazon", JobStatus.APPLIED, LocalDate.now().minusDays(3), null),
                createJob("Netflix", JobStatus.APPLIED, LocalDate.now().minusDays(4), null),
                createJob("Spotify", JobStatus.APPLIED, LocalDate.now().minusDays(5), null)
        );
        given(jobRepository.findAllByUser(testUser)).willReturn(jobs);

        // when
        DashboardResponse response = dashboardService.getDashboard(testUser);

        // then - return just 5 jobs
        assertThat(response.getRecentJobs()).hasSize(5);
        assertThat(response.getRecentJobs().get(0).getCompanyName()).isEqualTo("Google");
    }

    // ==============upcomingInterviews==============

    @Test
    void getDashboard_upcomingInterviews_onlyFutureInterviews() {
        // given
        List<Job> jobs = List.of(
                createJob("Google", JobStatus.INTERVIEW, LocalDate.now(), LocalDateTime.now().plusDays(2)),
                createJob("Meta", JobStatus.INTERVIEW, LocalDate.now(), LocalDateTime.now().minusDays(1)),
                createJob("Apple", JobStatus.APPLIED, LocalDate.now(), null)
        );
        given(jobRepository.findAllByUser(testUser)).willReturn(jobs);

        // when
        DashboardResponse response = dashboardService.getDashboard(testUser);

        // then
        assertThat(response.getUpcomingInterviews()).hasSize(1);
        assertThat(response.getUpcomingInterviews().get(0).getCompanyName()).isEqualTo("Google");
    }

    @Test
    void getDashboard_upcomingInterviews_sortedByInterviewDateTimeAsc() {
        // given
        List<Job> jobs = List.of(
                createJob("Google", JobStatus.INTERVIEW, LocalDate.now(),
                        LocalDateTime.now().plusDays(3)),
                createJob("Meta", JobStatus.INTERVIEW, LocalDate.now(),
                        LocalDateTime.now().plusDays(1)),
                createJob("Apple", JobStatus.INTERVIEW, LocalDate.now(),
                        LocalDateTime.now().plusDays(2))
        );
        given(jobRepository.findAllByUser(testUser)).willReturn(jobs);

        // when
        DashboardResponse response = dashboardService.getDashboard(testUser);

        // then
        assertThat(response.getUpcomingInterviews().get(0).getCompanyName()).isEqualTo("Meta");
        assertThat(response.getUpcomingInterviews().get(1).getCompanyName()).isEqualTo("Apple");
        assertThat(response.getUpcomingInterviews().get(2).getCompanyName()).isEqualTo("Google");
    }

    @Test
    void getDashboard_upcomingInterviews_limitFive() {
        // given - 면접이 6개
        List<Job> jobs = List.of(
                createJob("Google", JobStatus.INTERVIEW, LocalDate.now(), LocalDateTime.now().plusDays(1)),
                createJob("Meta", JobStatus.INTERVIEW, LocalDate.now(), LocalDateTime.now().plusDays(2)),
                createJob("Apple", JobStatus.INTERVIEW, LocalDate.now(), LocalDateTime.now().plusDays(3)),
                createJob("Amazon", JobStatus.INTERVIEW, LocalDate.now(), LocalDateTime.now().plusDays(4)),
                createJob("Netflix", JobStatus.INTERVIEW, LocalDate.now(), LocalDateTime.now().plusDays(5)),
                createJob("Spotify", JobStatus.INTERVIEW, LocalDate.now(), LocalDateTime.now().plusDays(6))
        );
        given(jobRepository.findAllByUser(testUser)).willReturn(jobs);

        // when
        DashboardResponse response = dashboardService.getDashboard(testUser);

        // then - 5개만 반환
        assertThat(response.getUpcomingInterviews()).hasSize(5);
    }

    // ==============helperMethod==============

    private Job createJob(String companyName, JobStatus status,
                          LocalDate appliedAt, LocalDateTime interviewDateTime) {
        return Job.builder()
                .companyName(companyName)
                .position("Engineer")
                .status(status)
                .appliedAt(appliedAt)
                .interviewDateTime(interviewDateTime)
                .user(testUser)
                .build();
    }
}
