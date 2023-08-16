package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Reading;
import com.dongguk.ossdev.backend.dto.request.ReadingRequestDto;
import com.dongguk.ossdev.backend.dto.response.ReadingDto;
import com.dongguk.ossdev.backend.repository.ReadingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadingService {
    private final ReadingRepository readingRepository;

    @Transactional
    public ReadingDto create(ReadingRequestDto createRequest) {
        Reading createReadingEntity = createRequest.toEntity();
        readingRepository.save(createReadingEntity);
        ReadingDto createdDto = ReadingDto.builder()
                .id(createReadingEntity.getId())
                .grade(createReadingEntity.getGrade())
                .semester(createReadingEntity.getSemester())
                .title(createReadingEntity.getTitle())
                .subject(createReadingEntity.getSubject())
                .build();
        return createdDto;
    }

    public ReadingDto read(Long id) {
        Reading readingEntity = readingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("독서 활동 상황을 찾을 수 없습니다."));
        ReadingDto showDto = ReadingDto.builder()
                .id(readingEntity.getId())
                .grade(readingEntity.getGrade())
                .semester(readingEntity.getSemester())
                .title(readingEntity.getTitle())
                .subject(readingEntity.getSubject())
                .build();
        return showDto;
    }

    public List<ReadingDto> readAll() {
        List<Reading> readingEntityList = readingRepository.findAll();
        List<ReadingDto> showDtos = readingEntityList.stream()
                .map(reading -> ReadingDto.createReadingDto(reading))
                .collect(Collectors.toList());
        return showDtos;
    }

    @Transactional
    public ReadingDto update(Long id, ReadingRequestDto updateRequest) {
        Reading target = readingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("독서 활동 상황을 찾을 수 없습니다."));
        target.patch(updateRequest);
        Reading updateReadingEntity = updateRequest.toEntity();
        ReadingDto updatedDto = ReadingDto.builder()
                .id(updateReadingEntity.getId())
                .grade(updateReadingEntity.getGrade())
                .semester(updateReadingEntity.getSemester())
                .title(updateReadingEntity.getTitle())
                .subject(updateReadingEntity.getSubject())
                .build();
        return updatedDto;
    }

    @Transactional
    public ReadingDto delete(Long id) {
        Reading target = readingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("독서 활동 상황을 찾을 수 없습니다."));
        ReadingDto deletedDto = ReadingDto.builder()
                .id(target.getId())
                .grade(target.getGrade())
                .semester(target.getSemester())
                .title(target.getTitle())
                .subject(target.getSubject())
                .build();
        readingRepository.delete(target);
        return deletedDto;
    }
}
