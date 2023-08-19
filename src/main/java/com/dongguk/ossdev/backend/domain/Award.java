package com.dongguk.ossdev.backend.domain;

import com.dongguk.ossdev.backend.dto.request.AwardRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Award extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "award_id")
    private Long id;

    private String name;

    private String tier;

    private String date;

    private String institution;

    private String target;

    // ========= mapping =========

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_record_id")
    private SchoolRecord schoolRecord;
    
    @Builder
    public Award(String name, String tier, String date, String institution, String target, SchoolRecord schoolRecord) {
        this.name = name;
        this.tier = tier;
        this.date = date;
        this.institution = institution;
        this.target = target;
        this.schoolRecord = schoolRecord;
    }


    // ========= mapping method =========
    public void setSchoolRecord(SchoolRecord schoolRecord) {
        this.schoolRecord = schoolRecord;
        schoolRecord.getAwardList().add(this);
    }

    public static Award createAward(SchoolRecord schoolRecord) {
        Award award = new Award();
        award.setSchoolRecord(schoolRecord);
        return award;
    }

//    public void update(String name, String tier, String date, String institution, String target) {
//        this.name = name;
//        this.tier = tier;
//        this.date = date;
//        this.institution = institution;
//        this.target = target;
//    }

    public void update(AwardRequestDto awardDto) {
        this.name = awardDto.getName();
        this.tier = awardDto.getTier();
        this.date = awardDto.getDate();
        this.institution = awardDto.getInstitution();
        this.target = awardDto.getTarget();
    }

}
