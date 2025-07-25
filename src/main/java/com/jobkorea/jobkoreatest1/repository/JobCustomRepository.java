package com.jobkorea.jobkoreatest1.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.jobkorea.jobkoreatest1.dto.JobRes;
import com.jobkorea.jobkoreatest1.entity.Job;

public interface JobCustomRepository{
    public Page<JobRes> findBySection(String section, Pageable pageable);
}
