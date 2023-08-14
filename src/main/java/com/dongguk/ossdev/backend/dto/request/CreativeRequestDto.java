package com.dongguk.ossdev.backend.dto.request;

import com.dongguk.ossdev.backend.domain.Creative;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreativeRequestDto {
    private final int grade;  // 학년
    private final String area;    // 자율활동, 동아리활동,
    private final int activityTime;   // 활동 시간
    private final String specialty;   // 특기사항

    public Creative toEntity(SchoolRecord schoolRecord) {
        return Creative.builder()
                .schoolRecord(schoolRecord)
                .grade(grade)
                .activityTime(activityTime)
                .area(area)
                .specialty(specialty)
                .build();
    }
}
