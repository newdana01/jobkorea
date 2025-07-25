package com.jobkorea.jobkoreatest1.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jobkorea.jobkoreatest1.dto.JobRes;
import com.jobkorea.jobkoreatest1.entity.Job;
import com.jobkorea.jobkoreatest1.repository.JobRepository;
import com.jobkorea.jobkoreatest1.repository.JobCustomRepository;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ch.qos.logback.core.util.StringUtil;

import static com.jobkorea.jobkoreatest1.entity.QJob.job;
import static com.jobkorea.jobkoreatest1.entity.QCompany.company;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JobRepositoryImpl{
    private final JPAQueryFactory jpaQueryFactory;

    public Page<JobRes> findBySection(String section, Pageable pageable){
        JPAQuery<JobRes> query = jpaQueryFactory.select(
            Projections.constructor(JobRes.class,
            job.id,
            job.title,
            job.company.name,
            job.deadline,
            job.company.logoUrl,
            job.company.adLevel
                )
            ).from(job)
            .leftJoin(company).on(job.company.id.eq(company.id))
            .where(job.company.adLevel.eq(section))
            .groupBy(job.id);


        int count = query.fetch().size();
        List<JobRes> data = query.orderBy(job.deadline.asc())
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
        Pageable requestPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return new PageImpl(data, requestPageable, count);
    }
    
}
