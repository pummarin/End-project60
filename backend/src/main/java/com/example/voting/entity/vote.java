package com.example.voting.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@Entity
@Table(name = "VOTE")
public class vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VOTE_ID", insertable = true)
    private long id;

    private @NonNull Date vtime;
    private @NonNull Long score;
    


    public vote() {
    }

    public vote(Date vtime, Long score) {
        this.vtime = vtime;
        this.score = score;
    }
}