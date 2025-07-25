package com.jobkorea.jobkoreatest1.service;

import org.springframework.data.domain.Pageable;

import com.jobkorea.jobkoreatest1.common.dto.PaginatedDto;
import com.jobkorea.jobkoreatest1.dto.JobRes;

public interface JobService {
    public PaginatedDto<JobRes> getJobList(String keyword, Pageable pageable);
}
