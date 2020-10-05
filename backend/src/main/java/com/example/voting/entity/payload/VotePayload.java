package com.example.voting.entity.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VotePayload {

    @NotBlank
    private Long students_id;

    @NotBlank
    private Long can_id;

    private String hash;
}
