package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Creative;
import com.dongguk.ossdev.backend.domain.Educational;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import com.dongguk.ossdev.backend.dto.request.EducationalRequestDto;
import com.dongguk.ossdev.backend.dto.response.EducationalDto;
import com.dongguk.ossdev.backend.repository.EducationalRepository;
import com.dongguk.ossdev.backend.repository.SchoolRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationalService {
    private final SchoolRecordRepository schoolRecordRepository;
    private final EducationalRepository educationalRepository;

    public List<EducationalDto> create(Long schoolRecordId, List<EducationalRequestDto> createRequest) {
        SchoolRecord schoolRecord = schoolRecordRepository.findById(schoolRecordId)
                .orElseThrow(() -> new IllegalArgumentException("생활기록부를 찾을 수 없습니다."));

        createRequest.stream().forEach(educationalRequestDto -> {
            educationalRepository.save(educationalRequestDto.toEntity(schoolRecord));
        });

        List<Educational> educationalList = educationalRepository.findBySchoolRecordId(schoolRecordId);
        return EducationalDto.createEducationalDtoList(educationalList);
    }

    public List<EducationalDto> read(Long schoolRecordId) {
        List<Educational> educationalEntityList = educationalRepository.findBySchoolRecordId(schoolRecordId);
        List<EducationalDto> showDtos = educationalEntityList.stream()
                .map(educational -> EducationalDto.createEducationalDto(educational))
                .collect(Collectors.toList());
        return showDtos;
    }

    @Transactional
    public EducationalDto update(Long id, EducationalRequestDto updateRequest) {
        Educational target = educationalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("교과학습 발달상황을 찾을 수 없습니다."));
        target.patch(updateRequest);
        Educational updateEducationalEntity = updateRequest.toEntity();
        EducationalDto updatedDto = EducationalDto.createEducationalDto(updateEducationalEntity);
        return updatedDto;
    }

    @Transactional
    public void deleteById(Long id) {
        educationalRepository.delete(
                educationalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("교과학습 발달상황을 찾을 수 없습니다."))
        );
    }
}
