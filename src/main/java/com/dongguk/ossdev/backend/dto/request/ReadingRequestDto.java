package com.dongguk.ossdev.backend.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor
public class ReadingRequestDto {
    private int grade;
    private String semester;
    private String title;
    private String subject;

    @Builder
    public ReadingRequestDto(int grade, String semester, String title, String subject) {
        this.grade = grade;
        this.semester = semester;
        this.title = title;
        this.subject = subject;
    }
}
