package com.jobkorea.jobkoreatest1.entity;

import java.time.LocalDate;

import com.jobkorea.jobkoreatest1.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "jobs")
@Getter
@Builder
public class Job extends BaseEntity{
    @Id
    @Column(name = "job_id")
    private Long id;
    private String title;
    private LocalDate deadline;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
