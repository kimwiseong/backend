package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Award;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AwardDto {
    private final Long id;
    private final String name;
    private final String tier;
    private final String date;
    private final String institution;
    private final String target;

    @Builder
    public AwardDto(Long id, String name, String tier, String date, String institution, String target) {
        this.id = id;
        this.name = name;
        this.tier = tier;
        this.date = date;
        this.institution = institution;
        this.target = target;
    }

    public static AwardDto createAwardDto(Award award) {
        return new AwardDto(
                award.getId(),
                award.getName(),
                award.getTier(),
                award.getDate(),
                award.getInstitution(),
                award.getTarget()
        );
    }

    public static List<AwardDto> createAwardDtoList(List<Award> awardList) {
        return awardList.stream().map(award -> new AwardDto(
                award.getId(),
                award.getName(),
                award.getTier(),
                award.getDate(),
                award.getInstitution(),
                award.getTarget()
        )).collect(Collectors.toList());
    }
}
