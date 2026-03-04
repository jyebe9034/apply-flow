package com.hannah.applyflow.job.repository;

import com.hannah.applyflow.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryCustom {
}
