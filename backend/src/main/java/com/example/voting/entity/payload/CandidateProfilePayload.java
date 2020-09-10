package com.example.voting.entity.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CandidateProfilePayload {

    @NotBlank
    private String title_name;

    @NotBlank
    private String c_name;

    @NotBlank
    private String student_id;

    @NotBlank
    private int year;

    @NotBlank
    private String grade;

    @NotBlank
    private String archivement;

    @NotBlank
    private String position;

    @NotBlank
    private Long party_id;

    @NotBlank
    private Long admins_id;

    @NotBlank
    private Long gender_id;

    @NotBlank
    private Long major_id;



}
