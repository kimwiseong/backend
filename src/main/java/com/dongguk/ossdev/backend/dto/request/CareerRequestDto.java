package com.dongguk.ossdev.backend.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class CareerRequestDto {
    private String name;
    private int grade;
    private String specialtyOrInterest;
    private String studentHope;
    private String parentHope;
    private String reason;

    @Builder
    public CareerRequestDto(String name, int grade, String specialtyOrInterest, String studentHope, String parentHope, String reason) {
        this.name = name;
        this.grade = grade;
        this.specialtyOrInterest = specialtyOrInterest;
        this.studentHope = studentHope;
        this.parentHope = parentHope;
        this.reason = reason;
    }
}
