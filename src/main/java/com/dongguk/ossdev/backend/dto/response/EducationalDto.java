package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    
}
