package com.dongguk.ossdev.backend.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class CommonQuestionDto {
    Long id;
    String commonQuestion;
    boolean isUsed;

    @Builder
    public CommonQuestionDto(Long id, String commonQuestion, boolean isUsed) {
        this.id = id;
        this.commonQuestion = commonQuestion;
        this.isUsed = isUsed;
    }
}
