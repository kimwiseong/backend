package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ReadingDto {
    private final Long id;
    private final int grade;
    private final String semester;
    private final String title;
    private final String subject;


}
