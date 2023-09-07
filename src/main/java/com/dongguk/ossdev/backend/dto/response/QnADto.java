package com.dongguk.ossdev.backend.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class QnADto {
    private final Long id;
    private final Long interviewId;
    private final String question;
    private final String answer;
    private final Timestamp createdDate;
}
