package com.hannah.applyflow.job.repository;

import com.hannah.applyflow.job.Job;
import com.hannah.applyflow.job.dto.JobSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobRepositoryCustom {

    Page<Job> search(JobSearchCondition condition, Pageable pageable);
}
