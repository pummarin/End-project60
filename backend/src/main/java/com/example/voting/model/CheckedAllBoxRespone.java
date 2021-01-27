package com.example.voting.model;

import lombok.Data;

@Data
public class CheckedAllBoxRespone {
    private Long id;
    private String hash;
    private String prevHash;
    private Boolean correct;
}
