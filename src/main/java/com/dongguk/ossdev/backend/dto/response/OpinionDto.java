package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Opinion;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<OpinionDto> createOpinionDtoList(List<Opinion> opinionList) {
        return opinionList.stream().map(opinion -> new OpinionDto(
                opinion.getId(),
                opinion.getGrade(),
                opinion.getContent()
        )).collect(Collectors.toList());
    }
}
