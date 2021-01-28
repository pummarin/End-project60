package com.example.voting.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckedAllBoxRespone {
    private Long id;
    private String hash;
    private String prevHash;
    private Boolean correct;
    private LocalDateTime voteTime;
}
