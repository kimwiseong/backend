package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Career;
import com.dongguk.ossdev.backend.domain.Creative;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreativeDto {
    private final Long id;
    private final int grade;  // 학년
    private final String area;    // 자율활동, 동아리활동,
    private final int activityTime;   // 활동 시간
    private final String specialty;   // 특기사항

    public static CreativeDto createCreativeDto(Creative creative) {
        return new CreativeDto(
                creative.getId(),
                creative.getGrade(),
                creative.getArea(),
                creative.getActivityTime(),
                creative.getSpecialty()
        );
    }

    public static List<CreativeDto> createCreativeDtoList(List<Creative> creativeList) {
        return creativeList.stream().map(creative -> new CreativeDto(
                creative.getId(),
                creative.getGrade(),
                creative.getArea(),
                creative.getActivityTime(),
                creative.getSpecialty()
        )).collect(Collectors.toList());
    }

}
