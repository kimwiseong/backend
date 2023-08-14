package com.dongguk.ossdev.backend.dto.request;

import com.dongguk.ossdev.backend.domain.Educational;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EducationalRequestDto {
    private final int grade;
    private final String semester;
    private final String subject;
    private final String course;
    private final int rank;
    private final String detailAndSpecialty;

    public Educational toEntity(SchoolRecord schoolRecord) {
        return Educational.builder()
                .schoolRecord(schoolRecord)
                .grade(grade)
                .semester(semester)
                .subject(subject)
                .course(course)
                .rank(rank)
                .detailAndSpecialty(detailAndSpecialty)
                .build();
    }
}
