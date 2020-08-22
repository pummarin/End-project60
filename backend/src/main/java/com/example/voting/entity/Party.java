package com.example.voting.entity;

import lombok.*;

import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@Table(name = "Party")
public class Party {

    @Id
	@SequenceGenerator(name = "party_seq", sequenceName = "party_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "party_seq")
    @Column(name = "PARTY_ID", unique = true, nullable = true)
    private Long party_id;
    
	private String p_name;
    private int member;
    private String policy;
    private int p_number;
    private Long point;

    public Party(){}

}