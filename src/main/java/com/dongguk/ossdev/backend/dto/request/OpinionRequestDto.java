package com.dongguk.ossdev.backend.dto.request;

import com.dongguk.ossdev.backend.domain.Opinion;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OpinionRequestDto {
    private final int grade;
    private final String content;

    public Opinion toEntity() {
        return Opinion.builder()
                .grade(grade)
                .content(content)
                .build();
    }
}
