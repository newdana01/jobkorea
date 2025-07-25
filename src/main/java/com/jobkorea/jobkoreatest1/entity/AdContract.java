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

@Entity(name = "ad_contracts")
@Builder
@Getter
public class AdContract extends BaseEntity{
    @Id
    @Column(name = "contract_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;
    private String section;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private String status;
}
