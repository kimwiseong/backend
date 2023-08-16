package com.dongguk.ossdev.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Creative extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creative_id")
    private Long id;

    private int grade;

    private String area;

    private int activityTime;

    @Lob
    private String specialty;

    // ========= mapping =========

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_record_id")
    private SchoolRecord schoolRecord;

    @Builder
    public Creative(int grade, String area, int activityTime, String specialty, SchoolRecord schoolRecord) {
        this.grade = grade;
        this.area = area;
        this.activityTime = activityTime;
        this.specialty = specialty;
        this.schoolRecord = schoolRecord;
    }

    // ========= mapping method =========
    public void setSchoolRecord(SchoolRecord schoolRecord) {
        this.schoolRecord = schoolRecord;
        schoolRecord.getCreativeList().add(this);
    }
}
