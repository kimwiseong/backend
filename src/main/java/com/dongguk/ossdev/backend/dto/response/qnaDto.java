package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class qnaDto {
    Long id;
    Long interviewId;
    String question;
    String answer;
    Timestamp createdDate;

    @Builder
    public qnaDto(Long id, Long interviewId, String question, String answer, Timestamp createdDate) {
        this.id = id;
        this.interviewId = interviewId;
        this.question = question;
        this.answer = answer;
        this.createdDate = createdDate;
    }
}
