package com.dongguk.ossdev.backend.dto.request;

import com.dongguk.ossdev.backend.domain.Career;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CareerRequestDto {
    private final String name;
    private final int grade;
    private final String specialtyOrInterest;
    private final String studentHope;
    private final String parentHope;
    private final String reason;

    public Career toEntity(SchoolRecord schoolRecord) {
        return Career.builder()
                .schoolRecord(schoolRecord)
                .grade(grade)
                .specialtyOrInterest(specialtyOrInterest)
                .studentHope(studentHope)
                .parentHope(parentHope)
                .reason(reason)
                .build();
    }
}
