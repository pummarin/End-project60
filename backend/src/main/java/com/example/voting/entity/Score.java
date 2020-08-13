package com.example.vote.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Score_ID",insertable = true)
    private long vote_id;

    private long student_id;
    private long party_id;
    private long score;
    
    public Score() {
	}

	public Score(Long student_id, Long party_id, Long score) {
		this.student_id = student_id;
		this.party_id = party_id;
		this.score = score;
	}

}