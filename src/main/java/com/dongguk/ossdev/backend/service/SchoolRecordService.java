package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.*;
import com.dongguk.ossdev.backend.dto.response.*;
import com.dongguk.ossdev.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolRecordService {

    private final SchoolRecordRepository schoolRecordRepository;
    private final UserRepository userRepository;
    private final AwardRepository awardRepository;
    private final CareerRepository careerRepository;
    private final CreativeRepository creativeRepository;
    private final EducationalRepository educationalRepository;
    private final ReadingRepository readingRepository;
    private final OpinionRepository opinionRepository;

    public SchoolRecordDto create(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        if (user.getSchoolRecord().getId() != null) {
            log.info("기록부 이미 있는 놈");
            return SchoolRecordDto.builder()
                    .schoolRecordId(user.getSchoolRecord().getId())
                    .build();
        }

        // 연관 관계 설정
        SchoolRecord schoolRecord = SchoolRecord.createSchoolRecord(user);
        schoolRecordRepository.save(schoolRecord);


        return SchoolRecordDto.builder()
                .schoolRecordId(schoolRecord.getId())
                .build();
    }

    public SchoolRecordDto read(Long userId) {
        SchoolRecord schoolRecord = schoolRecordRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("생활기록부를 찾을 수 없습니다."));

        return SchoolRecordDto.createSchoolRecordDto(schoolRecord);
    }

    public SchoolRecordDto readBySort(Long userId) {
        Long schoolRecordId = schoolRecordRepository.findSchoolRecordIdByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("생활기록부를 찾을 수 없습니다."));;

        List<AwardDto> awardDtoList = AwardDto.createAwardDtoList(
                awardRepository.findBySchoolRecordId(schoolRecordId, Sort.by(Sort.Direction.ASC,"date")));

        if (awardDtoList.isEmpty()) {
            throw new IllegalArgumentException("수상 경력를 찾을 수 없습니다.");
        }

        List<CareerDto> careerDtoList = CareerDto.createCareerDtoList(
                careerRepository.findBySchoolRecordId(schoolRecordId, Sort.by(Sort.Direction.ASC,"grade")));

        if (careerDtoList.isEmpty()) {
            throw new IllegalArgumentException("진로 희망 사항를 찾을 수 없습니다.");
        }

        List<CreativeDto> creativeDtoList = CreativeDto.createCreativeDtoList(
                creativeRepository.findBySchoolRecordId(schoolRecordId, Sort.by(Sort.Direction.ASC,"grade")));

        if (creativeDtoList.isEmpty()) {
            throw new IllegalArgumentException("창의적 체험활동 상황을 찾을 수 없습니다.");
        }

        List<EducationalDto> educationalDtoList = EducationalDto.createEducationalDtoList(
                educationalRepository.findBySchoolRecordId(schoolRecordId, Sort.by(Sort.Direction.ASC,"grade")));

        if (educationalDtoList.isEmpty()) {
            throw new IllegalArgumentException("교과학습 발달상황을 찾을 수 없습니다.");
        }

        List<ReadingDto> readingDtoList = ReadingDto.createReadingDtoList(
                readingRepository.findBySchoolRecordId(schoolRecordId, Sort.by(Sort.Direction.ASC,"grade")));

        if (readingDtoList.isEmpty()) {
            throw new IllegalArgumentException("독서 활동상황을 찾을 수 없습니다.");
        }

        List<OpinionDto> opinionDtoList = OpinionDto.createOpinionDtoList(
                opinionRepository.findBySchoolRecordId(schoolRecordId, Sort.by(Sort.Direction.ASC,"grade")));

        if (opinionDtoList.isEmpty()) {
            throw new IllegalArgumentException("행동특성 및 종합의견을 찾을 수 없습니다.");
        }

        return SchoolRecordDto.builder()
                .schoolRecordId(schoolRecordId)
                .awardDtoList(awardDtoList)
                .careerDtoList(careerDtoList)
                .creativeDtoList(creativeDtoList)
                .educationalDtoList(educationalDtoList)
                .readingDtoList(readingDtoList)
                .opinionDtoList(opinionDtoList)
                .build();
    }
}
