package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.SchoolRecord;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SchoolRecordDto {
    private final Long schoolRecordId;

    private final List<AwardDto> awardDtoList;

    private final List<CareerDto> careerList;

    private final List<CreativeDto> creativeList;

    private final List<EducationalDto> educationalDtoList;

    private final List<ReadingDto> readingDtoList;

    private final List<OpinionDto> opinionDtoList;



    @Builder
    public SchoolRecordDto(Long schoolRecordId,
                           List<AwardDto> awardDtoList,
                           List<CareerDto> careerDtoList,
                           List<CreativeDto> creativeDtoList,
                           List<EducationalDto> educationalDtoList,
                           List<ReadingDto> readingDtoList,
                           List<OpinionDto> opinionDtoList) {
        this.schoolRecordId = schoolRecordId;
        this.awardDtoList = awardDtoList;
        this.careerList = careerDtoList;
        this.creativeList = creativeDtoList;
        this.educationalDtoList = educationalDtoList;
        this.readingDtoList = readingDtoList;
        this.opinionDtoList = opinionDtoList;
    }

    public static SchoolRecordDto createSchoolRecordDto(SchoolRecord schoolRecord) {

        return new SchoolRecordDto(
                        schoolRecord.getId(),
                        AwardDto.createAwardDtoList(schoolRecord.getAwardList()),
                        CareerDto.createCareerDtoList(schoolRecord.getCareerList()),
                        CreativeDto.createCreativeDtoList(schoolRecord.getCreativeList()),
                        EducationalDto.createEducationalDtoList(schoolRecord.getEducationalList()),
                        ReadingDto.createReadingDtoList(schoolRecord.getReadingList()),
                        OpinionDto.createOpinionDtoList(schoolRecord.getOpinionList())
                );
    }



}
