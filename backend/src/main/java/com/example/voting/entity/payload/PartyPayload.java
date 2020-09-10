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
    private String p_number;

    
    private int point;

    @NotBlank
    private Long admins_id;    

}
