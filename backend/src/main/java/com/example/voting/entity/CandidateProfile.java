package com.example.voting.entity;

import lombok.*;

import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@Table(name="CandidateProfile")


public class CandidateProfile {
    @Id
    @SequenceGenerator(name="candidate_profile_seq",sequenceName="candidate_profile_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="candidate_profile_seq")
    @Column(name = "CANDIDATE_PROFILE_ID", unique = true, nullable = true)
    private  Long p_id;
    
    private String title_name;
    private  String c_name;
    private  int age;
    private  float grade;
    private  String skill;
    private  String status;

    private Gender gender;


    public CandidateProfile(){}
}