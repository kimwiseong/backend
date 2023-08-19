package com.dongguk.ossdev.backend.domain;

import com.dongguk.ossdev.backend.dto.request.CareerRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Career extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_id")
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

    // ========= mapping method =========
    public void setSchoolRecord(SchoolRecord schoolRecord) {
        this.schoolRecord = schoolRecord;
        schoolRecord.getCareerList().add(this);
    }

    public void update(CareerRequestDto careerDto) {
        this.grade = careerDto.getGrade();
        this.specialtyOrInterest = careerDto.getSpecialtyOrInterest();
        this.studentHope = careerDto.getStudentHope();
        this.parentHope = careerDto.getParentHope();
        this.reason = careerDto.getReason();
    }
}
