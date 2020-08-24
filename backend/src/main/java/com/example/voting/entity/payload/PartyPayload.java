package com.example.voting.entity.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PartyPayload {

    @NotBlank
    private String p_name;

    @NotBlank
    private String policy;

    @NotBlank
    private int p_number;

    @NotBlank
    private Long point;

    @NotBlank
    private Long admins_id;    

}
