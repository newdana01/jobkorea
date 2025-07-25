package com.jobkorea.jobkoreatest1.common.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaginatedDto<T> {
    private List<T> data; // 데이터 리스트
    private int count; // 현재 데이터 수
    private int total; // 전체 데이터 수
    private int page; // 현재 페이지
    private int size; // 한 페이지 당 불러올 데이터 개수
}
