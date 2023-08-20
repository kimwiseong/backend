package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.config.ChatGptConfig;
import com.dongguk.ossdev.backend.domain.ChatGptMessage;
import com.dongguk.ossdev.backend.dto.request.ChatGptRequestDto;
import com.dongguk.ossdev.backend.dto.request.QuestionRequestDto;
import com.dongguk.ossdev.backend.dto.response.ChatGptResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatGptService {
    @Value("${chatgpt.api.token}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + apiKey);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);
        //답변이 길어질 경우 TimeOut Error가 발생하니 1분정도 설정
        requestFactory.setReadTimeout(60 * 1000);   //  1min = 60 sec * 1,000ms
        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.CHAT_URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);

        return responseEntity.getBody();
    }

//    public ChatGptResponse askQuestion(QuestionRequest questionRequest){
//        List<ChatGptMessage> messages = new ArrayList<>();
//        messages.add(ChatGptMessage.builder()
//                .role(ChatGptConfig.ROLE)
//                .content(questionRequest.getQuestion())
//                .build());
//        return this.getResponse(
//                this.buildHttpEntity(
//                        new ChatGptRequest(
//                                ChatGptConfig.CHAT_MODEL,
//                                ChatGptConfig.MAX_TOKEN,
//                                ChatGptConfig.TEMPERATURE,
//                                ChatGptConfig.STREAM,
//                                messages
//                                //ChatGptConfig.TOP_P
//                        )
//                )
//        );
//    }
//
//    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
//        List<ChatGptMessage> messages = new ArrayList<>();
//        messages.add(ChatGptMessage.builder()
//                .role(ChatGptConfig.ROLE)
//                .content(questionRequest.getQuestion())
//                .build());
//        return this.getResponse(
//                this.buildHttpEntity(ChatGptRequestDto.builder()
//                                .model(ChatGptConfig.CHAT_MODEL)
//                                .prompt(requestDto.getQuestion())
//                                .maxTokens(ChatGptConfig.MAX_TOKEN)
//                                .temperature(ChatGptConfig.TEMPERATURE)
//                                .topP(ChatGptConfig.TOP_P)
//                                .stream(ChatGptConfig.STREAM)
//                                .messages()
//                                .build()
//                )
//        );
//    }
}
