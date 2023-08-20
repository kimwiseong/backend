package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ChatGptResponseDto {
    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Message> messages;

    @Builder
    public ChatGptResponseDto(String id, String object, LocalDate created, String model, List<Message> messages) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.messages = messages;
    }

    @Setter
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("completion_tokens")
        private int completionTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;
    }

    @Setter
    public static class Choice {
        private ChatGptMessage message;
        @JsonProperty("finish_reason")
        private String finishReason;
        private int index;
    }
}
