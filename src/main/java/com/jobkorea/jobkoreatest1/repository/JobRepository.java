package com.jobkorea.jobkoreatest1.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobkorea.jobkoreatest1.dto.JobRes;
import com.jobkorea.jobkoreatest1.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>, JobCustomRepository{
    // public Page<JobRes> findBySection(String section, Pageable pageable);
}
