package com.example.voting.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.NotNull;


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
    
    @NotNull
    private String title_name;
    @NotNull
    private  String c_name;
    @NotNull
    private String student_id;
    @NotNull
    private int year;
    @NotNull
    private  float grade;
    @NotNull
    private  String archivement;
    @NotNull
    private  String position;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Major.class)
    @JoinColumn(name = "MAJOR_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Major major; 

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Gender gender; 
 
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Party.class)
    @JoinColumn(name = "PARTY_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Party party;

    @OneToOne(fetch = FetchType.EAGER,targetEntity = Admins.class)
    @JoinColumn(name = "ADMIN_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Admins admins;
    
    public CandidateProfile(){}

    public CandidateProfile(String title_name, String c_name, String student_id, int year, float grade,
                            String archivement, String position, Party party, Major major, Gender gender, Admins admins) {
        this.title_name = title_name;
        this.c_name = c_name;
        this.student_id = student_id;
        this.major = major;
        this.year = year;
        this.grade = grade;
        this.archivement = archivement;
        this.position = position;
        this.party = party;
        this.gender = gender;
        this.admins = admins;
		
	}
}