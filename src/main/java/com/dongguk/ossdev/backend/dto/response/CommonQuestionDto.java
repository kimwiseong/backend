package com.dongguk.ossdev.backend.dto.response;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class CommonQuestionDto {
    private final Long id;
    private final String commonQuestion;
    private final boolean isUsed;

}
