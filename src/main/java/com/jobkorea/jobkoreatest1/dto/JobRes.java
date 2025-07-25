package com.jobkorea.jobkoreatest1.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jobkorea.jobkoreatest1.entity.Job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobRes {
    private Long jobId;
    private String title;
    private String companyName;
    private String deadline;
    private String logoUrl;
    private String adLevel;


    public JobRes(
        Long jobId, 
        String title, 
        String companyName,
        LocalDate deadline,
        String logoUrl,
        String adLevel
        ) {
        this.jobId = jobId;
        this.title = title;
        this.companyName = companyName;
        this.deadline = deadline.toString();
        this.logoUrl = logoUrl;
        this.adLevel = adLevel;
    }

    
}
