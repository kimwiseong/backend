package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Award;
import com.dongguk.ossdev.backend.domain.Career;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class CareerDto {
    private final Long id;
    private final int grade;
    private final String specialtyOrInterest;
    private final String studentHope;
    private final String parentHope;
    private final String reason;

    public static CareerDto createCareerDto(Career career) {
        return new CareerDto(
                career.getId(),
                career.getGrade(),
                career.getSpecialtyOrInterest(),
                career.getStudentHope(),
                career.getParentHope(),
                career.getReason()
        );
    }

    public static List<CareerDto> createCareerDtoList(List<Career> careerList) {
        return careerList.stream().map(career -> new CareerDto(
                career.getId(),
                career.getGrade(),
                career.getSpecialtyOrInterest(),
                career.getStudentHope(),
                career.getParentHope(),
                career.getReason()
        )).collect(Collectors.toList());
    }

}
