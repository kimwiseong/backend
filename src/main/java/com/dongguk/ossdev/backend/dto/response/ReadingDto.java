package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor
public class ReadingDto {
    private Long id;
    private int grade;
    private String semester;
    private String title;
    private String subject;

    @Builder
    public ReadingDto(Long id, int grade, String semester, String title, String subject) {
        this.id = id;
        this.grade = grade;
        this.semester = semester;
        this.title = title;
        this.subject = subject;
    }
}
