package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Career;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import com.dongguk.ossdev.backend.dto.request.CareerRequestDto;
import com.dongguk.ossdev.backend.dto.response.AwardDto;
import com.dongguk.ossdev.backend.dto.response.CareerDto;
import com.dongguk.ossdev.backend.repository.CareerRepository;
import com.dongguk.ossdev.backend.repository.SchoolRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CareerService {

    private final SchoolRecordRepository schoolRecordRepository;
    private final CareerRepository careerRepository;


    public List<CareerDto> create(Long schoolRecordId, List<CareerRequestDto> careerRequestDtoList) {

        SchoolRecord schoolRecord = schoolRecordRepository.findById(schoolRecordId)
                .orElseThrow(() -> new IllegalArgumentException("생활기록부를 찾을 수 없습니다."));

        careerRequestDtoList.stream().forEach(careerRequestDto -> {
            careerRepository.save(careerRequestDto.toEntity(schoolRecord));
        });

        List<Career> careerList = careerRepository.findBySchoolRecordId(schoolRecordId);
        if (careerList.isEmpty()) {
            throw new IllegalArgumentException("진로 희망 사항을 찾을 수 없습니다.");
        }

        return CareerDto.createCareerDtoList(careerList);
    }

    public List<CareerDto> read(Long schoolRecordId) {
        return Optional.ofNullable(careerRepository.findBySchoolRecordId(schoolRecordId))
                .filter(list -> !list.isEmpty())
                .map(CareerDto::createCareerDtoList)
                .orElseThrow(() -> new IllegalArgumentException("수상 경력을 찾을 수 없습니다."));
    }

    public CareerDto updateById(Long careerId, CareerRequestDto careerRequestDto) {
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new IllegalArgumentException("수정할 진로 희망 사항을 찾을 수 없습니다."));

        career.update(careerRequestDto);
        return CareerDto.createCareerDto(career);
    }

    public void deleteById(Long careerId) {
        careerRepository.delete(
                careerRepository.findById(careerId)
                        .orElseThrow(() -> new IllegalArgumentException("삭제할 진로 희망 사항을 찾을 수 없습니다."))
        );
    }

}
