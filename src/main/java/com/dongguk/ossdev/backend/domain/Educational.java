package com.dongguk.ossdev.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Educational extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "educational_id")
    private Long id;

    private int grade;

    private String semester;

    private String subject;

    private String course;

    private int rank;

    @Lob
    private String detailAndSpecialty;

    // ========= mapping =========

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_record_id")
    private SchoolRecord schoolRecord;

    @Builder
    public Educational(User user, int grade, String semester, String subject, String course, int rank, String detailAndSpecialty, SchoolRecord schoolRecord) {
        this.grade = grade;
        this.semester = semester;
        this.subject = subject;
        this.course = course;
        this.rank = rank;
        this.detailAndSpecialty = detailAndSpecialty;
        this.schoolRecord = schoolRecord;
    }

    // ========= mapping method =========
    public void setSchoolRecord(SchoolRecord schoolRecord) {
        this.schoolRecord = schoolRecord;
        schoolRecord.getEducationalList().add(this);
    }
}
