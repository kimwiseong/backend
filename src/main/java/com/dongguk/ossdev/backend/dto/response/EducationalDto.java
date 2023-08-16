package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Educational;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EducationalDto {
    private final Long id;
    private final int grade;
    private final String semester;
    private final String subject;
    private final String course;
    private final int rank;
    private final String detailAndSpecialty;

    public static EducationalDto createEducationalDto(Educational educational) {
        return new EducationalDto(
                educational.getId(),
                educational.getGrade(),
                educational.getSemester(),
                educational.getSubject(),
                educational.getCourse(),
                educational.getRank(),
                educational.getDetailAndSpecialty()
        );
    }
}
