package com.dongguk.ossdev.backend.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class CreativeRequestDto {
    private int grade;  // 학년
    private String area;    // 자율활동, 동아리활동,
    private int activityTime;   // 활동 시간
    private String specialty;   // 특기사항

    @Builder
    public CreativeRequestDto(int grade, String area, int activityTime, String specialty) {
        this.grade = grade;
        this.area = area;
        this.activityTime = activityTime;
        this.specialty = specialty;
    }
}
