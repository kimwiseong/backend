package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CareerDto {
    private final Long id;
    private final String name;
    private final int grade;
    private final String specialtyOrInterest;
    private final String studentHope;
    private final String parentHope;
    private final String reason;

}
