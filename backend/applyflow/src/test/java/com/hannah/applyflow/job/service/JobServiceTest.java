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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    private User testUser;
    private User anotherUser;
    private Job testJob;
    private JobCreateRequest createRequest;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .email("hannah@test.com")
                .build();

        anotherUser = User.builder()
                .id(2L)
                .email("other@test.com")
                .build();

        testJob = Job.builder()
                .id(1L)
                .companyName("Google")
                .position("Backend Engineer")
                .appliedAt(LocalDate.now())
                .user(testUser)
                .build();

        createRequest = JobCreateRequest.builder()
                .companyName("Google")
                .position("Backend Engineer")
                .status(JobStatus.APPLIED)
                .appliedAt(LocalDate.now())
                .build();
    }

    // ==============createJob==============

    @Test
    void createJob_success() {
        // given
        given(jobRepository.save(any(Job.class))).willReturn(testJob);

        // when
        JobResponse response = jobService.createJob(testUser, createRequest);

        // then
        assertThat(response.getCompanyName()).isEqualTo("Google");
        assertThat(response.getPosition()).isEqualTo("Backend Engineer");
        assertThat(response.getStatus()).isEqualTo(JobStatus.APPLIED);
        verify(jobRepository, times(1)).save(any(Job.class));
    }

    @Test
    void getJobById_success() {
        // given
        given(jobRepository.findById(1L)).willReturn(Optional.of(testJob));

        // when
        JobResponse response = jobService.getJobById(1L, testUser);

        // then
        assertThat(response.getCompanyName()).isEqualTo("Google");
        assertThat(response.getPosition()).isEqualTo("Backend Engineer");
    }

    @Test
    void getJobById_notFound_throwsException() {
        // given
        given(jobRepository.findById(999L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> jobService.getJobById(999L, testUser))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.JOB_NOT_FOUND.getMessage());
    }

    @Test
    void getJobById_accessDenied_throwsException() {
        // given
        given(jobRepository.findById(1L)).willReturn(Optional.of(testJob));

        // when & then
        assertThatThrownBy(() -> jobService.getJobById(1L, anotherUser))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.ACCESS_DENIED.getMessage());
    }

    // ==============updateJob==============

    @Test
    void updateJob_success() {
        // given
        JobUpdateRequest updateRequest = JobUpdateRequest.builder()
                .position("Senior Backend Engineer")
                .status(JobStatus.INTERVIEW)
                .build();
        given(jobRepository.findById(1L)).willReturn(Optional.of(testJob));

        // when
        JobResponse response = jobService.updateJob(1L, updateRequest, testUser);

        // then
        assertThat(response.getPosition()).isEqualTo("Senior Backend Engineer");
        assertThat(response.getStatus()).isEqualTo(JobStatus.INTERVIEW);
    }

    @Test
    void updateJob_accessDenied_throwsException() {
        // given
        JobUpdateRequest updateRequest = JobUpdateRequest.builder()
                .position("Senior Backend Engineer")
                .build();
        given(jobRepository.findById(1L)).willReturn(Optional.of(testJob));

        // when & then
        assertThatThrownBy(() -> jobService.updateJob(1L, updateRequest, anotherUser))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.ACCESS_DENIED.getMessage());
    }

    @Test
    void updateJob_notFound_throwsException() {
        // given
        JobUpdateRequest updateRequest = JobUpdateRequest.builder()
                .position("Senior Backend Engineer")
                .build();
        given(jobRepository.findById(999L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> jobService.updateJob(999L, updateRequest, testUser))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.JOB_NOT_FOUND.getMessage());
    }

    // ==============deleteJob==============

    @Test
    void deleteJob_success() {
        // given
        given(jobRepository.findById(1L)).willReturn(Optional.of(testJob));

        // when
        jobService.deleteJob(1L, testUser);

        // then
        verify(jobRepository, times(1)).delete(testJob);
    }

    @Test
    void deleteJob_notFound_throwsException() {
        // given
        given(jobRepository.findById(999L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> jobService.deleteJob(999L, testUser))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.JOB_NOT_FOUND.getMessage());
    }

    @Test
    void deleteJob_accessDenied_throwsException() {
        // given
        given(jobRepository.findById(1L)).willReturn(Optional.of(testJob));

        // when & then
        assertThatThrownBy(() -> jobService.deleteJob(1L, anotherUser))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.ACCESS_DENIED.getMessage());
    }
}
