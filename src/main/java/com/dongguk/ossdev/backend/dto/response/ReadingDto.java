package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Reading;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class ReadingDto {
    private final Long id;
    private final int grade;
    private final String semester;
    private final String title;
    private final String subject;

    public static ReadingDto createReadingDto(Reading reading) {
        return new ReadingDto(
                reading.getId(),
                reading.getGrade(),
                reading.getSemester(),
                reading.getTitle(),
                reading.getSubject()
        );
    }

    public static List<ReadingDto> createReadingDtoList(List<Reading> readings) {
        return readings.stream().map(reading -> new ReadingDto(
                reading.getId(),
                reading.getGrade(),
                reading.getSemester(),
                reading.getTitle(),
                reading.getSubject()
        )).collect(Collectors.toList());
    }
}
