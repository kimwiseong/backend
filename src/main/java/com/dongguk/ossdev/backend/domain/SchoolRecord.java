package com.dongguk.ossdev.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class SchoolRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_record_id")
    private Long id;

    // ========= mapping =========

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "schoolRecord", cascade = ALL)
    private List<Award> awardList = new ArrayList<>();

    @OneToMany(mappedBy = "schoolRecord", cascade = ALL)
    private List<Career> careerList = new ArrayList<>();

    @OneToMany(mappedBy = "schoolRecord", cascade = ALL)
    private List<Creative> creativeList = new ArrayList<>();

    @OneToMany(mappedBy = "schoolRecord", cascade = ALL)
    private List<Educational> educationalList = new ArrayList<>();

    @OneToMany(mappedBy = "schoolRecord", cascade = ALL)
    private List<Reading> readingList = new ArrayList<>();

    @OneToMany(mappedBy = "schoolRecord", cascade = ALL)
    private List<Opinion> opinionList = new ArrayList<>();

    // ========= mapping method =========
    public void setUser(User user) {
        this.user = user;
        user.setSchoolRecord(this);
    }

}
