package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.GPTAnswer;
import com.dongguk.ossdev.backend.domain.GPTQuestion;
import com.dongguk.ossdev.backend.dto.request.ChatGptRequestDto;
import com.dongguk.ossdev.backend.dto.response.ChatGptResponseDto;
import com.dongguk.ossdev.backend.repository.GPTAnswerRepository;
import com.dongguk.ossdev.backend.repository.GPTQuestionRepository;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatGptService {
    private final OpenAiService openAiService;
    private final GPTAnswerRepository gptAnswerRepository;
    private final GPTQuestionRepository gptQuestionRepository;

    @Transactional
    public ChatGptResponseDto completionChat(ChatGptRequestDto gptCompletionChatRequest) {
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(
                ChatGptRequestDto.of(gptCompletionChatRequest));

        ChatGptResponseDto response = ChatGptResponseDto.of(chatCompletion);

        List<String> messages = response.getMessages().stream()
                .map(ChatGptResponseDto.Message::getMessage)
                .collect(Collectors.toList());

        GPTAnswer gptAnswer = saveAnswer(messages);

        saveQuestion(gptCompletionChatRequest.getMessage(), gptAnswer);

        return response;
    }

    private void saveQuestion(String question, GPTAnswer answer) {
        GPTQuestion questionEntity = new GPTQuestion(question, answer);
        gptQuestionRepository.save(questionEntity);
    }

    private GPTAnswer saveAnswer(List<String> response) {

        String answer = response.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining());

        return gptAnswerRepository.save(new GPTAnswer(answer));
    }
}
