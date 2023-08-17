package com.dongguk.ossdev.backend.domain;

import com.dongguk.ossdev.backend.dto.request.OpinionRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Opinion extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinion_id")
    private Long id;

    private int grade;

    @Lob
    private String content;

    // ========= mapping =========
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_record_id")
    private SchoolRecord schoolRecord;

    @Builder
    public Opinion(int grade, String content, SchoolRecord schoolRecord) {
        this.grade = grade;
        this.content = content;
        this.schoolRecord = schoolRecord;
    }

    // ========= mapping method =========
    public void setSchoolRecord(SchoolRecord schoolRecord) {
        this.schoolRecord = schoolRecord;
        schoolRecord.getOpinionList().add(this);
    }

    public void patch(OpinionRequestDto updateRequest) {
        Integer grade = updateRequest.getGrade();
        if (grade != null)
            this.grade = updateRequest.getGrade();
        if (updateRequest.getContent() != null)
            this.content = updateRequest.getContent();
    }
}
