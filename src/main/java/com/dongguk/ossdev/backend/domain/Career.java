package com.dongguk.ossdev.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int grade;

    private String specialtyOrInterest;

    private String studentHope;

    private String parentHope;

    @Lob
    private String reason;

    // ========= mapping =========

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_record_id")
    private SchoolRecord schoolRecord;

    @Builder
    public Career(int grade, String specialtyOrInterest, String studentHope, String parentHope, String reason, SchoolRecord schoolRecord) {
        this.grade = grade;
        this.specialtyOrInterest = specialtyOrInterest;
        this.studentHope = studentHope;
        this.parentHope = parentHope;
        this.reason = reason;
        this.schoolRecord = schoolRecord;
    }
}
