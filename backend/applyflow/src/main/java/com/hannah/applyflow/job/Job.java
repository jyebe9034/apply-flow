package com.hannah.applyflow.job;

import com.hannah.applyflow.global.entity.BaseTimeEntity;
import com.hannah.applyflow.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Job extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status;

    private LocalDate appliedAt;

    @Column(length = 1000)
    private String jobUrl;

    private Long salary;

    @Column(length = 100)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private JobPlatform platform;

    @Column(length = 100)
    private String recruiterName;

    @Column(length = 150)
    private String recruiterEmail;

    @Column(length = 30)
    private String recruiterPhone;

    private LocalDateTime interviewDateTime;

    @Column(length = 1000)
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
