package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Reading;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Getter
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

    public static ReadingDto createReadingDto(Reading reading) {
        return new ReadingDto(
                reading.getId(),
                reading.getGrade(),
                reading.getSemester(),
                reading.getTitle(),
                reading.getSubject()
        );
    }
}
