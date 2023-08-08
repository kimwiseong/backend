package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class EducationalDto {
    private int grade;
    private String semester;
    private String subject;
    private String course;
    private int rank;
    private String detailAndSpecialty;

    @Builder
    public EducationalDto(int grade, String semester, String subject, String course, int rank, String detailAndSpecialty) {
        this.grade = grade;
        this.semester = semester;
        this.subject = subject;
        this.course = course;
        this.rank = rank;
        this.detailAndSpecialty = detailAndSpecialty;
    }
}
