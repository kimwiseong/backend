package com.dongguk.ossdev.backend.dto.request;

import com.dongguk.ossdev.backend.domain.ChatGptMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGptRequestDto {
    private String model;
    private String prompt;

    private Integer maxTokens;
    private Double temperature;

    private Double topP;    // 1.0
    private Boolean stream;
    private List<ChatGptMessage> messages;

    @Builder
    public ChatGptRequestDto(String model, String prompt, Integer maxTokens,
                             Double temperature, Double topP, List<ChatGptMessage> messages, boolean stream) {
        this.model = model;
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
        this.messages = messages;
        this.stream = stream;
    }
}
