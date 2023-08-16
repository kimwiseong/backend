package com.dongguk.ossdev.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
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

}
