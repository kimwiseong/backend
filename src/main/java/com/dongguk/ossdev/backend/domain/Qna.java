package com.dongguk.ossdev.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_id")
    private Long id;

    @Lob
    private String question;

    @Lob
    private String answer;

    private Timestamp createDate;

    // ========= mapping =========

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "interview_id")
    private Interview interview;

    // ========= mapping method =========

    public void setInterview(Interview interview) {
        this.interview = interview;
        interview.getQnaList().add(this);
    }
}
