package com.dongguk.ossdev.backend.domain;

import com.dongguk.ossdev.backend.dto.request.UserRequestDto;
import com.dongguk.ossdev.backend.type.LoginProvider;
import com.dongguk.ossdev.backend.type.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

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
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    private String socialId;

    @Enumerated(EnumType.STRING)
    private LoginProvider provider;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private UserRole role;

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

    @Builder
    public User(String name, String email, String password, String socialId, LoginProvider provider, boolean isGraduate,
                String refreshToken, UserRole role, Boolean isLogin, Boolean isValid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.socialId = socialId;
        this.provider = provider;
        this.isGraduate = isGraduate;
        this.refreshToken = refreshToken;
        this.role = role;
        this.isLogin = isLogin;
        this.isValid = isValid;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void update(UserRequestDto userRequestDto) {
        this.name = userRequestDto.getName();
        this.email = userRequestDto.getEmail();
        this.isGraduate = userRequestDto.getIsGraduate();
    }

    public void delete() {
        this.isValid = false;
    }
}
