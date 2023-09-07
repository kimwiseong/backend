package com.dongguk.ossdev.backend.dto.request;

import com.dongguk.ossdev.backend.domain.Reading;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ReadingRequestDto {
    private final int grade;
    private final String semester;
    private final String title;
    private final String subject;

    public Reading toEntity() {
        return Reading.builder()
                .grade(grade)
                .subject(subject)
                .title(title)
                .semester(semester)
                .build();
    }

    public Reading toEntity(SchoolRecord schoolRecord) {
        return Reading.builder()
                .schoolRecord(schoolRecord)
                .grade(grade)
                .subject(subject)
                .title(title)
                .semester(semester)
                .build();
    }

}
