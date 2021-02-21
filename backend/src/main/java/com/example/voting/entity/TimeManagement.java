package com.example.voting.entity;

import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

// import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@Table(name="TimeManagement")
public class TimeManagement {
    
    @Id
    @SequenceGenerator(name="time_management_seq",sequenceName="time_management_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="time_management_seq")  
    @Column(name = "time_id", unique = true, nullable = true)
    private  Long time_id;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time_start;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time_end;

    @OneToOne(fetch = FetchType.EAGER,targetEntity = Admin.class)
    @JoinColumn(name = "ADMIN_ID", insertable = true)
    private @NotNull Admin admin;
}
