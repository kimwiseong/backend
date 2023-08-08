package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class InterviewDto {
    private Long id;
    private Long userId;
    private Timestamp createdDate;

    @Builder
    public InterviewDto(Long id, Long userId, Timestamp createdDate) {
        this.id = id;
        this.userId = userId;
        this.createdDate = createdDate;
    }
}
