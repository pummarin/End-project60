package com.example.voting.entity;

import java.util.Date;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

@Data
@Entity
@Table(name = "VOTE")
public class Vote {
    @Id
    @SequenceGenerator(name = "VOTE_seq", sequenceName = "VOTE_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTE_seq")
    @Column(name = "VOTE_ID", unique = true, nullable = true)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER,targetEntity = Students.class)
    @JoinColumn(name = "STUDENT_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Students students;

    @OneToOne(fetch = FetchType.EAGER,targetEntity = Party.class)
    @JoinColumn(name = "PARTY_ID", insertable = true)
    @JsonManagedReference
    private @NonNull Party party;


    private @NonNull Date voteTime;



    public Vote() {
    }

    public Vote( Students students, Party party) {
        this.voteTime = new Date();
        this.students = students;
        this.party = party;
    }
}