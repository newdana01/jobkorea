package com.jobkorea.jobkoreatest1.entity;

import java.time.LocalDate;

import com.jobkorea.jobkoreatest1.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "companies")
@Builder
@Getter
public class Company extends BaseEntity{
    @Id
    @Column(name = "company_id")
    private Long id;
    private String name;
    @Column(name = "logo_url")
    private String logoUrl;
    @Column(name = "ad_level")
    private String adLevel;
}
