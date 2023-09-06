package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.*;
import com.dongguk.ossdev.backend.dto.request.ChatGptRequestDto;
import com.dongguk.ossdev.backend.dto.response.ChatGptResponseDto;
import com.dongguk.ossdev.backend.repository.GPTAnswerRepository;
import com.dongguk.ossdev.backend.repository.GPTQuestionRepository;
import com.dongguk.ossdev.backend.repository.SchoolRecordRepository;
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

    private final SchoolRecordRepository schoolRecordRepository;


    @Transactional
    public ChatGptResponseDto completionChat(Long userId, ChatGptRequestDto gptCompletionChatRequest) {
        // DB에서 정보를 가져와서 ChatGptRequestDto에 넣는 부분을 추가
        SchoolRecord schoolRecord = schoolRecordRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("not found school record"));

        String award = "수상 경력\n";
        List<Award> awardList = schoolRecord.getAwardList();
        for (Award awardEntity : awardList) {
            award += "- " + awardEntity.getName() + ", " + awardEntity.getTier() + " \n";
        }

        String career = "진로 희망 사항\n";
        List<Career> careerList = schoolRecord.getCareerList();
        for (Career careerEntity : careerList) {
            career += "- " + careerEntity.getSpecialtyOrInterest() + ", " + careerEntity.getStudentHope() + ", " + careerEntity.getReason() + " \n";
        }

        String creative = "창의적 체험 활동\n";
        List<Creative> creativeList = schoolRecord.getCreativeList();
        for (Creative creativeEntity : creativeList) {
            creative += "- " + creativeEntity.getActivityTime() + ", " + creativeEntity.getArea() + ", " + creativeEntity.getSpecialty() + " \n";
        }

        String educational = "교과 학습 발달 상황\n";
        List<Educational> educationalList = schoolRecord.getEducationalList();
        for (Educational educationalEntity : educationalList) {
            educational += "- " + educationalEntity.getGrade() + ", " + educationalEntity.getSemester() + ", " + educationalEntity.getSubject() + ", " + educationalEntity.getCourse() + ", " + educationalEntity.getRank() + ", " + educationalEntity.getDetailAndSpecialty() + " \n";
        }

        String reading = "독서 활동 상황\n";
        List<Reading> readingList = schoolRecord.getReadingList();
        for (Reading readingEntity : readingList) {
            reading += "- " + readingEntity.getTitle() + ", " + readingEntity.getSemester() + " \n";
        }

        String opinion = "행동 특성 및 종합 의견\n";
        List<Opinion> opinionList = schoolRecord.getOpinionList();
        for (Opinion opinionEntity : opinionList) {
            opinion += "- " + opinionEntity.getGrade() + ", " + opinionEntity.getContent() + " \n";
        }

        String question = "위와 같은 생활 기록부를 가지고 있는 청각장애인에게 질문을 4개 만 생성해줘.\n" +
                "청각장애인은 대학 입시를 준비하는 사람이야. 질문만 생성하고 다른 말은 붙이지 마.";

        String prompt = award + career + creative + educational + reading + opinion + question + "\n";

        gptCompletionChatRequest.setMessage(prompt);
        gptCompletionChatRequest.setModel("gpt-3.5-turbo");
        gptCompletionChatRequest.setMaxTokens(1000);

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
