package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class CareerDto {
    private Long id;
    private String name;
    private int grade;
    private String specialtyOrInterest;
    private String studentHope;
    private String parentHope;
    private String reason;

    @Builder
    public CareerDto(Long id, String name, int grade, String specialtyOrInterest, String studentHope, String parentHope, String reason) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.specialtyOrInterest = specialtyOrInterest;
        this.studentHope = studentHope;
        this.parentHope = parentHope;
        this.reason = reason;
    }
}
