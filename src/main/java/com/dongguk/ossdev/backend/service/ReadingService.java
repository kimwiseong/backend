package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Opinion;
import com.dongguk.ossdev.backend.domain.Reading;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import com.dongguk.ossdev.backend.dto.request.ReadingRequestDto;
import com.dongguk.ossdev.backend.dto.response.OpinionDto;
import com.dongguk.ossdev.backend.dto.response.ReadingDto;
import com.dongguk.ossdev.backend.repository.ReadingRepository;
import com.dongguk.ossdev.backend.repository.SchoolRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadingService {
    private final SchoolRecordRepository schoolRecordRepository;
    private final ReadingRepository readingRepository;


    public List<ReadingDto> create(Long schoolRecordId, List<ReadingRequestDto> createRequest) {
        SchoolRecord schoolRecord = schoolRecordRepository.findById(schoolRecordId)
                .orElseThrow(() -> new IllegalArgumentException("생활기록부를 찾을 수 없습니다."));

        createRequest.stream().forEach(readingRequestDto -> {
            readingRepository.save(readingRequestDto.toEntity(schoolRecord));
        });

        List<Reading> readingList = readingRepository.findBySchoolRecordId(schoolRecordId);
        return ReadingDto.createReadingDtoList(readingList);
    }

    public List<ReadingDto> read(Long schoolRecordId) {
        List<Reading> readingEntityList = readingRepository.findBySchoolRecordId(schoolRecordId);
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
    public void deleteById(Long id) {
        readingRepository.delete(readingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("독서 활동 상황을 찾을 수 없습니다.")));
    }
}
