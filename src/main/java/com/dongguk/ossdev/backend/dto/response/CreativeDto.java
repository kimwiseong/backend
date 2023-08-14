package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreativeDto {
    private final Long id;
    private final int grade;  // 학년
    private final String area;    // 자율활동, 동아리활동,
    private final int activityTime;   // 활동 시간
    private final String specialty;   // 특기사항

}
