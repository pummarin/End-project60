package com.example.voting.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


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
    private @NotNull Students students;




    private @NotNull Date voteTime;



    public Vote() {
    }

    public Vote( Students students) {
        this.voteTime = new Date();
        this.students = students;
    }
}