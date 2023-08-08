package com.dongguk.ossdev.backend.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class AwardRequestDto {
    private String name;    // 수상명
    private String tier;    // 수상등급
    private String date;    // 수상연월일
    private String institution; // 수여 기관
    private String target;  // 참가 대상

    @Builder
    public AwardRequestDto(String name, String tier, String date, String institution, String target) {
        this.name = name;
        this.tier = tier;
        this.date = date;
        this.institution = institution;
        this.target = target;
    }
}
