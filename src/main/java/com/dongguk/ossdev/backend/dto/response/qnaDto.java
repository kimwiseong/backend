package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class qnaDto {
    private final Long id;
    private final Long interviewId;
    private final String question;
    private final String answer;
    private final Timestamp createdDate;


}
