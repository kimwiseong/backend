package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Career;
import com.dongguk.ossdev.backend.domain.Creative;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SchoolRecordDto {
    private final Long school_record_id;

    private final List<AwardDto> awardDtoList;

    private final List<CareerDto> careerList;

    private final List<CreativeDto> creativeList;
//
//    private final List<EducationalDto> educationalDtoList = new ArrayList<>();
//
//    private final List<ReadingDto> readingDtoList = new ArrayList<>();
//
//    private final List<OpinionDto> opinionDtoList = new ArrayList<>();



    @Builder
    public SchoolRecordDto(Long school_record_id, List<AwardDto> awardDtoList, List<CareerDto> careerDtoList, List<CreativeDto> creativeDtoList) {
        this.school_record_id = school_record_id;
        this.awardDtoList = awardDtoList;
        this.careerList = careerDtoList;
        this.creativeList = creativeDtoList;
    }

    public static SchoolRecordDto createSchoolRecordDto(SchoolRecord schoolRecord) {

        return new SchoolRecordDto(
                schoolRecord.getId(),
                AwardDto.createAwardDtoList(schoolRecord.getAwardList()),
                CareerDto.createCareerDtoList(schoolRecord.getCareerList()),
                CreativeDto.createCreativeDtoList(schoolRecord.getCreativeList())
        );
    }



}
