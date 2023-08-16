package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Educational;
import com.dongguk.ossdev.backend.domain.Opinion;
import com.dongguk.ossdev.backend.dto.request.EducationalRequestDto;
import com.dongguk.ossdev.backend.dto.response.EducationalDto;
import com.dongguk.ossdev.backend.dto.response.OpinionDto;
import com.dongguk.ossdev.backend.repository.EducationalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationalService {
    private final EducationalRepository educationalRepository;

    @Transactional
    public List<EducationalDto> create(List<EducationalRequestDto> createRequest) {
        List<Educational> createEntityList = createRequest.stream()
                .map(createEducational -> createEducational.toEntity())
                .collect(Collectors.toList());

        createEntityList.stream()
                .map(saveEntity -> educationalRepository.save(saveEntity))
                .collect(Collectors.toList());

        List<EducationalDto> createDtos = createEntityList.stream()
                .map(educational -> EducationalDto.createEducationalDto(educational))
                .collect(Collectors.toList());
        return createDtos;
    }

    public List<EducationalDto> readAll() {
        List<Educational> educationalEntityList = educationalRepository.findAll();
        List<EducationalDto> showDtos = educationalEntityList.stream()
                .map(educational -> EducationalDto.createEducationalDto(educational))
                .collect(Collectors.toList());
        return showDtos;
    }

    public EducationalDto read(Long id) {
        Educational educationalEntity = educationalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("교과학습발달상황을 찾을 수 없습니다."));
        EducationalDto showDto = EducationalDto.createEducationalDto(educationalEntity);
        return showDto;
    }

    @Transactional
    public EducationalDto update(Long id, EducationalRequestDto updateRequest) {
        Educational target = educationalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("교과학습발달상황을 찾을 수 없습니다."));
        target.patch(updateRequest);
        Educational updateEducationalEntity = updateRequest.toEntity();
        EducationalDto updatedDto = EducationalDto.createEducationalDto(updateEducationalEntity);
        return updatedDto;
    }

    @Transactional
    public EducationalDto delete(Long id) {
        Educational target = educationalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("독서 활동 상황을 찾을 수 없습니다."));
        EducationalDto deletedDto = EducationalDto.createEducationalDto(target);
        educationalRepository.delete(target);
        return deletedDto;
    }
}
