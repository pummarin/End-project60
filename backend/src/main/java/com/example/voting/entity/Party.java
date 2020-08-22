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


    @OneToOne(fetch = FetchType.EAGER,targetEntity = Admins.class)
    @JoinColumn(name = "ADMIN_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Admins admins;

    public Party(){}

    public Party(String p_name, String policy, int p_number, Long point) {
        this.p_name = p_name;
        this.policy = policy;
        this.p_number = p_number;
        this.point = point;
    
	}

}