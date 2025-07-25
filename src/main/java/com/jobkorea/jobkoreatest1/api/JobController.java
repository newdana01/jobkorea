package com.jobkorea.jobkoreatest1.api;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobkorea.jobkoreatest1.common.api.BaseController;
import com.jobkorea.jobkoreatest1.common.dto.PaginatedDto;
import com.jobkorea.jobkoreatest1.dto.JobRes;
import com.jobkorea.jobkoreatest1.service.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JobController extends BaseController{
    private final JobService jobService;


    @GetMapping("/jobs")
    public PaginatedDto<JobRes> getJobList(
        @RequestParam(value = "section", required = false) String section,
        @PageableDefault(sort = "deadline", size = 12, page = 0) Pageable pageable
    ){
        return jobService.getJobList(section, pageable);
    }
}
