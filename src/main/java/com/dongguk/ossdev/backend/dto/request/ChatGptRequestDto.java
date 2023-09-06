package com.dongguk.ossdev.backend.dto.request;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptRequestDto {
    private String model;

    private String role;

    private String message;

    private Integer maxTokens;

    public static ChatCompletionRequest of(ChatGptRequestDto request) {
        return ChatCompletionRequest.builder()
                .model(request.getModel())
                .messages(convertChatMessage(request))
                .maxTokens(request.getMaxTokens())
                .build();
    }

    private static List<ChatMessage> convertChatMessage(ChatGptRequestDto request) {
        return List.of(new ChatMessage(request.getRole(), request.getMessage()));
    }
}
