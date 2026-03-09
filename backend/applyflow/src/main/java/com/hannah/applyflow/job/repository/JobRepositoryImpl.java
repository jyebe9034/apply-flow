package com.hannah.applyflow.job.repository;

import com.hannah.applyflow.job.Job;
import com.hannah.applyflow.job.QJob;
import com.hannah.applyflow.job.dto.JobSearchCondition;
import com.hannah.applyflow.user.QUser;
import com.hannah.applyflow.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobRepositoryImpl implements JobRepositoryCustom { // QueryDsl 추가

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Job> findAllByUser(User user, Pageable pageable) {
        QJob job = QJob.job;
        QUser qUser = QUser.user;

        List<Job> contents = queryFactory
                .selectFrom(job)
                .join(job.user, qUser).fetchJoin()
                .where(job.user.eq(user))
                .offset(pageable.getOffset()) // 페이지 시작 위치
                .limit(pageable.getPageSize()) // 가져올 개수
                .fetch();

        Long count = queryFactory
                .select(job.count())
                .from(job)
                .where(job.user.eq(user))
                .fetchOne();

        long total = count != null ? count : 0L; // fetchOne()이 null일 수도 있음

        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public Page<Job> search(JobSearchCondition condition, Pageable pageable) {
        return null;
    }


}
