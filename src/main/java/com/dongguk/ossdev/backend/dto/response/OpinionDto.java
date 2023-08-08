package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class OpinionDto {
    private Long id;
    private int grade;
    private String content;

    @Builder
    public OpinionDto(Long id, int grade, String content) {
        this.id = id;
        this.grade = grade;
        this.content = content;
    }
}
