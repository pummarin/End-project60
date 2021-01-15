package com.example.voting.entity;

import lombok.Data;

@Data
public class CandidateScoreResponeModel {
    private CandidateProfile candidateProfile;
    private int score = 0;

}
