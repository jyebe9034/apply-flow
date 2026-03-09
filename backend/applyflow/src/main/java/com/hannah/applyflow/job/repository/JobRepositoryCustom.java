package com.hannah.applyflow.job.repository;

import com.hannah.applyflow.job.Job;
import com.hannah.applyflow.job.dto.JobSearchCondition;
import com.hannah.applyflow.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobRepositoryCustom {

    Page<Job> findAllByUser(User user, Pageable pageable);

    Page<Job> search(JobSearchCondition condition, Pageable pageable);
}
