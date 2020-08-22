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
    @Column(name = "CAN_ID", unique = true, nullable = true)
    private  Long can_id;
    
    private String title_name;
    private  String c_name;
    private String student_id;
    private String major;
    private int year;
    private  float grade;
    private  String archivement;
    private  String position;

    /*
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    @JsonManagedReference
    private Gender gender; */

    
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Party.class)
    @JoinColumn(name = "PARTY_ID", insertable = true)
    @JsonManagedReference
    private Party party;

    @OneToOne(fetch = FetchType.EAGER,targetEntity = Admins.class)
    @JoinColumn(name = "ADMIN_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Admins admins;
    
    public CandidateProfile(){}

    public CandidateProfile(String title_name, String c_name, String student_id, String major, int year, float grade,
                            String archivement, String position, Party party, Admins admins) {
        this.title_name = title_name;
        this.c_name = c_name;
        this.student_id = student_id;
        this.major = major;
        this.year = year;
        this.grade = grade;
        this.archivement = archivement;
        this.position = position;
        this.party = party;
        this.admins = admin;
		
	}
}