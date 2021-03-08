package com.example.voting.entity.payload;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;

@Data
public class TimePayload {
    
    @NotBlank
    private LocalDate election_day;

    @NotBlank
    private LocalTime time_start;

    @NotBlank
    private LocalTime time_end;

    @NotBlank
    private Long admin_id;

}
