package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.QuestionRequestDto;
import com.dongguk.ossdev.backend.dto.response.ChatGptResponseDto;
import com.dongguk.ossdev.backend.service.ChatGptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/chat-gpt")
@RequiredArgsConstructor
public class ChatGptController {
    private final ChatGptService chatGptService;

    @PostMapping("/question")
    public ChatGptResponseDto sendQuestion(Locale locale, HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody QuestionRequestDto questionRequest) {
        ChatGptResponseDto chatGptResponse = chatGptService.askQuestion(questionRequest);

        //return 부분은 자유롭게 수정하시면됩니다. ex)return chatGptResponse;
        return chatGptResponse;
    }
}
