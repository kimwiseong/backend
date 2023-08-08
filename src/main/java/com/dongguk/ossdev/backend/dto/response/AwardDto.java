package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class AwardDto {
    private Long id;
    private String name;
    private String tier;
    private String date;
    private String institution;
    private String target;

    @Builder
    public AwardDto(Long id, String name, String tier, String date, String institution, String target) {
        this.id = id;
        this.name = name;
        this.tier = tier;
        this.date = date;
        this.institution = institution;
        this.target = target;
    }
}
