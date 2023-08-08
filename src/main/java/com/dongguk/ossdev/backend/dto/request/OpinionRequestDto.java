package com.dongguk.ossdev.backend.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class OpinionRequestDto {
    private int grade;
    private String content;

    @Builder
    public OpinionRequestDto(int grade, String content) {
        this.grade = grade;
        this.content = content;
    }
}
