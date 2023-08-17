package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.Reading;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SchoolRecordDto {
    private final Long school_record_id;

    @Builder
    public SchoolRecordDto(Long school_record_id) {
        this.school_record_id = school_record_id;
    }

}
