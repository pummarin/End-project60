package com.example.voting.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "VOTE")
public class Vote {
    @Id
    @SequenceGenerator(name = "VOTE_seq", sequenceName = "VOTE_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTE_seq")
    @Column(name = "VOTE_ID", unique = true, nullable = true)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Students.class)
    @JoinColumn(name = "STUDENT_ID", insertable = true)
//    @JsonManagedReference
    private @NotNull Students students;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = CandidateProfile.class)
    @JoinColumn(name = "CAN_ID", insertable = true)
//    @JsonManagedReference
    private @NotNull CandidateProfile candidateProfile;

    //    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    private @NotNull Date voteTime;

    @Column(name = "CREATED_DATE")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime voteTime;

    private String hash;

    private String prevHash;

    public Vote() {
    }

    public Vote(Students students, CandidateProfile candidateProfile, String hash, String prevHash) {
        this.voteTime = LocalDateTime.now();
        this.students = students;
        this.candidateProfile = candidateProfile;
        this.hash = hash;
        this.prevHash = prevHash;
    }
}