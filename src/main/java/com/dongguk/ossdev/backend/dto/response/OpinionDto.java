package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Opinion;
import com.dongguk.ossdev.backend.domain.Reading;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OpinionDto {
    private final Long id;
    private final int grade;
    private final String content;

    public static OpinionDto createOpinionDto(Opinion opinion) {
        return new OpinionDto(
                opinion.getId(),
                opinion.getGrade(),
                opinion.getContent()
        );
    }
}
