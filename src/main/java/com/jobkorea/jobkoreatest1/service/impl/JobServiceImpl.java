package com.jobkorea.jobkoreatest1.service.impl;
import java.lang.module.ModuleDescriptor.Builder;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobkorea.jobkoreatest1.common.dto.PaginatedDto;
import com.jobkorea.jobkoreatest1.dto.JobRes;
import com.jobkorea.jobkoreatest1.repository.JobRepository;
import com.jobkorea.jobkoreatest1.service.JobService;

import lombok.RequiredArgsConstructor;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{
    private final JobRepository jobRepository;

    @Override
    public PaginatedDto<JobRes> getJobList(String section, Pageable pageable) {
        Page<JobRes> page = jobRepository.findBySection(section, pageable);
        return PaginatedDto.<JobRes>builder()
        .data(page.getContent())
        .count(page.getNumberOfElements())
        .total((int)page.getTotalElements())
        .page(page.getNumber())
        .size(page.getSize())
        .build();
    }

}