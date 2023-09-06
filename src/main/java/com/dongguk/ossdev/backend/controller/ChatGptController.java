package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.ChatGptRequestDto;
import com.dongguk.ossdev.backend.dto.response.ChatGptResponseDto;
import com.dongguk.ossdev.backend.service.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/chatgpt")
@RequiredArgsConstructor
public class ChatGptController {
    private final ChatGptService chatGptService;

    @PostMapping("/completion/chat/{userId}")
    public ChatGptResponseDto completionChat(@PathVariable Long userId, final @RequestBody ChatGptRequestDto gptCompletionChatRequest) {

        return chatGptService.completionChat(userId, gptCompletionChatRequest);
    }
}
