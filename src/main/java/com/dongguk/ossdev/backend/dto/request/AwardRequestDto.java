package com.dongguk.ossdev.backend.dto.request;

import com.dongguk.ossdev.backend.domain.Award;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class AwardRequestDto {
    private final String name;    // 수상명
    private final String tier;    // 수상등급
    private final String date;    // 수상연월일
    private final String institution; // 수여 기관
    private final String target;  // 참가 대상

    public Award toEntity(SchoolRecord schoolRecord) {
        return Award.builder()
                .schoolRecord(schoolRecord)
                .name(name)
                .tier(tier)
                .date(date)
                .institution(institution)
                .target(target)
                .build();
    }
}
