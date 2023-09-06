package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.ChatGptRequestDto;
import com.dongguk.ossdev.backend.dto.response.ChatGptResponseDto;
import com.dongguk.ossdev.backend.service.ChatGptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/chatgpt")
@RequiredArgsConstructor
public class ChatGptController {
    private final ChatGptService chatGptService;

    @PostMapping("/completion/chat")
    public ChatGptResponseDto completionChat(HttpServletRequest request, final @RequestBody ChatGptRequestDto gptCompletionChatRequest) {
        Long userId = Long.valueOf(request.getAttribute("USER_ID").toString());
        return chatGptService.completionChat(userId, gptCompletionChatRequest);
    }
}
