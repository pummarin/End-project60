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
@Table(name="CandidateManagement")
public class CandidateManagement {

    @Id
    @SequenceGenerator(name="candidate_management_seq",sequenceName="candidate_management_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="candidate_management_seq")  
    private  Long c_id;

    public CandidateManagement(){}
}