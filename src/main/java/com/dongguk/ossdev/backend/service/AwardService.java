package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Award;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import com.dongguk.ossdev.backend.dto.request.AwardRequestDto;
import com.dongguk.ossdev.backend.dto.response.AwardDto;
import com.dongguk.ossdev.backend.repository.AwardRepository;
import com.dongguk.ossdev.backend.repository.SchoolRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwardService {

    private final SchoolRecordRepository schoolRecordRepository;
    private final AwardRepository awardRepository;


    public List<AwardDto> create(Long schoolRecordId, List<AwardRequestDto> awardRequestDtoList) {

        SchoolRecord schoolRecord = schoolRecordRepository.findById(schoolRecordId)
                .orElseThrow(() -> new IllegalArgumentException("생활기록부를 찾을 수 없습니다."));

        awardRequestDtoList.stream().forEach(awardRequestDto -> {
            awardRequestDto.toEntity().setSchoolRecord(schoolRecord);
            awardRepository.save(awardRequestDto.toEntity());
        });

        List<Award> awardList = awardRepository.findBySchoolRecordId(schoolRecordId);
        if (awardList.isEmpty()) {
            throw new IllegalArgumentException("수상 경력을 찾을 수 없습니다.");
        }

        return AwardDto.createAwardDtoList(awardList);
    }

    public List<AwardDto> read(Long schoolRecordId) {
        return Optional.ofNullable(awardRepository.findBySchoolRecordId(schoolRecordId))
                .filter(list -> !list.isEmpty())
                .map(AwardDto::createAwardDtoList)
                .orElseThrow(() -> new IllegalArgumentException("수상 경력을 찾을 수 없습니다."));
    }

    @Transactional
    public AwardDto updateById(Long awardId, AwardRequestDto awardRequestDto) {
        Award award = awardRepository.findById(awardId)
                .orElseThrow(() -> new IllegalArgumentException("수정할 수상 경력을 찾을 수 없습니다."));

        award.update(awardRequestDto);
        return AwardDto.createAwardDto(award);
    }

    public void deleteById(Long awardId) {
        awardRepository.delete(
                awardRepository.findById(awardId).orElseThrow(() -> new IllegalArgumentException("삭제할 수상 경력을 찾을 수 없습니다."))
        );
    }

}
