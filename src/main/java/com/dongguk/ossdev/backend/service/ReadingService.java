package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Reading;
import com.dongguk.ossdev.backend.dto.request.ReadingRequestDto;
import com.dongguk.ossdev.backend.dto.response.ReadingDto;
import com.dongguk.ossdev.backend.repository.ReadingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadingService {
    private final ReadingRepository readingRepository;

    @Transactional
    public List<ReadingDto> create(List<ReadingRequestDto> createRequest) {
        List<Reading> createEntityList = createRequest.stream()
                .map(createReading -> createReading.toEntity())
                .collect(Collectors.toList());

        createEntityList.stream()
                .map(saveEntity -> readingRepository.save(saveEntity))
                        .collect(Collectors.toList());

        List<ReadingDto> createDtos = createEntityList.stream()
                .map(reading -> ReadingDto.createReadingDto(reading))
                .collect(Collectors.toList());
        return createDtos;
    }

    public ReadingDto read(Long id) {
        Reading readingEntity = readingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("독서 활동 상황을 찾을 수 없습니다."));
        ReadingDto showDto = ReadingDto.createReadingDto(readingEntity);
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
        ReadingDto updatedDto = ReadingDto.createReadingDto(updateReadingEntity);
        return updatedDto;
    }

    @Transactional
    public ReadingDto delete(Long id) {
        Reading target = readingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("독서 활동 상황을 찾을 수 없습니다."));
        ReadingDto deletedDto = ReadingDto.createReadingDto(target);
        readingRepository.delete(target);
        return deletedDto;
    }
}
