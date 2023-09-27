package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Educational;
import com.dongguk.ossdev.backend.domain.Opinion;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import com.dongguk.ossdev.backend.dto.request.OpinionRequestDto;
import com.dongguk.ossdev.backend.dto.response.EducationalDto;
import com.dongguk.ossdev.backend.dto.response.OpinionDto;

import com.dongguk.ossdev.backend.repository.OpinionRepository;
import com.dongguk.ossdev.backend.repository.SchoolRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpinionService {
    private final SchoolRecordRepository schoolRecordRepository;
    private final OpinionRepository opinionRepository;

    public List<OpinionDto> create(Long schoolRecordId, List<OpinionRequestDto> createRequest) {
        SchoolRecord schoolRecord = schoolRecordRepository.findById(schoolRecordId)
                .orElseThrow(() -> new IllegalArgumentException("생활기록부를 찾을 수 없습니다."));

        createRequest.stream().forEach(opinionRequestDto -> {
            opinionRepository.save(opinionRequestDto.toEntity(schoolRecord));
        });

        List<Opinion> opinionList = opinionRepository.findBySchoolRecordId(schoolRecordId);
        return OpinionDto.createOpinionDtoList(opinionList);
    }

    public List<OpinionDto> read(Long schoolRecordId) {
        List<Opinion> opinionEntityList = opinionRepository.findBySchoolRecordId(schoolRecordId);
        List<OpinionDto> showDtos = opinionEntityList.stream()
                .map(opinion -> OpinionDto.createOpinionDto(opinion))
                .collect(Collectors.toList());
        return showDtos;
    }

    @Transactional
    public OpinionDto update(Long id, OpinionRequestDto updateRequest) {
        Opinion target = opinionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("행동 특성 및 종합의견을 찾을 수 없습니다."));
        target.patch(updateRequest);
        Opinion updateOpinionEntity = updateRequest.toEntity();
        OpinionDto updatedDto = OpinionDto.createOpinionDto(updateOpinionEntity);
        return updatedDto;
    }

    @Transactional
    public void deleteById(Long id) {
        opinionRepository.delete(opinionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("행동 특성 및 종합의견을 찾을 수 없습니다.")));
    }
}
