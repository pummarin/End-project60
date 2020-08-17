package com.example.voting.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Table(name="CandidateProfile")


public class CandidateProfile {
    @Id
    @SequenceGenerator(name="candidate_profile_seq",sequenceName="candidate_profile_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="candidate_profile_seq")  
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