package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class AwardDto {
    private final Long id;
    private final String name;
    private final String tier;
    private final String date;
    private final String institution;
    private final String target;

}
