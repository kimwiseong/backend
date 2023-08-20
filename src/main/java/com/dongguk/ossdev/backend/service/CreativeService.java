package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Career;
import com.dongguk.ossdev.backend.domain.Creative;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import com.dongguk.ossdev.backend.dto.request.CareerRequestDto;
import com.dongguk.ossdev.backend.dto.request.CreativeRequestDto;
import com.dongguk.ossdev.backend.dto.response.CareerDto;
import com.dongguk.ossdev.backend.dto.response.CreativeDto;
import com.dongguk.ossdev.backend.repository.CreativeRepository;
import com.dongguk.ossdev.backend.repository.SchoolRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreativeService {

    private final SchoolRecordRepository schoolRecordRepository;
    private final CreativeRepository creativeRepository;

    public List<CreativeDto> create(Long schoolRecordId, List<CreativeRequestDto> creativeRequestDtoList) {

        SchoolRecord schoolRecord = schoolRecordRepository.findById(schoolRecordId)
                .orElseThrow(() -> new IllegalArgumentException("생활기록부를 찾을 수 없습니다."));

        creativeRequestDtoList.stream().forEach(creativeRequestDto -> {
            creativeRequestDto.toEntity().setSchoolRecord(schoolRecord);
            creativeRepository.save(creativeRequestDto.toEntity());
        });

        List<Creative> creativeList = creativeRepository.findBySchoolRecordId(schoolRecordId);
        if (creativeList.isEmpty()) {
            throw new IllegalArgumentException("창의적 체험활동상황을 찾을 수 없습니다.");
        }


        return CreativeDto.createCreativeDtoList(creativeList);
    }

    public List<CreativeDto> read(Long schoolRecordId) {
        return Optional.ofNullable(creativeRepository.findBySchoolRecordId(schoolRecordId))
                .filter(list -> !list.isEmpty())
                .map(CreativeDto::createCreativeDtoList)
                .orElseThrow(() -> new IllegalArgumentException("수상 경력을 찾을 수 없습니다."));
    }

    public CreativeDto updateById(Long creativeId, CreativeRequestDto creativeRequestDto) {
        Creative creative = creativeRepository.findById(creativeId)
                .orElseThrow(() -> new IllegalArgumentException("수정할 창의적 체험활동상황을 찾을 수 없습니다."));

        creative.update(creativeRequestDto);
        return CreativeDto.createCreativeDto(creative);
    }

    public void deleteById(Long creativeId) {
        creativeRepository.delete(
                creativeRepository.findById(creativeId)
                        .orElseThrow(() -> new IllegalArgumentException("삭제할 창의적 체험활동상황을 찾을 수 없습니다."))
        );
    }
}
