package com.dongguk.ossdev.backend.domain;

import com.dongguk.ossdev.backend.domain.type.Oauth2Provider;
import com.dongguk.ossdev.backend.domain.type.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    private String socialId;

    @Enumerated(EnumType.STRING)
    private Oauth2Provider provider;

    private String refreshToken;

    private String accessToken;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private Timestamp createDate;

    private Boolean isGraduate;

    private Boolean isLogin;

    private Boolean isValid;

    // ========= mapping =========

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Interview> interviewList = new ArrayList<>();


    @JoinColumn(name = "school_record_id")
    @OneToOne(mappedBy = "user", fetch = LAZY)
    private SchoolRecord schoolRecord;


    // ========= mapping method =========
    public void setSchoolRecord(SchoolRecord schoolRecord) {
        this.schoolRecord = schoolRecord;
    }

}
