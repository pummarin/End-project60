package com.example.voting.entity;

import lombok.Data;

@Data
@Entity
@Table(name = "VOTE")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VOTE_ID", insertable = true)
    private long id;

    private @NotNull Date vtime;
    private @NotNull Long score;
    


    public Vote() {
    }

    public Vote(Date vtime, Long score) {
        this.vtime = vtime;
        this.score = score;
    }
}