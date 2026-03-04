package com.hannah.applyflow.job.dto;

import com.hannah.applyflow.job.JobStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobSearchCondition {

    private String companyName;
    private JobStatus status;
    private String position;
    private LocalDateTime appliedAt;
}
