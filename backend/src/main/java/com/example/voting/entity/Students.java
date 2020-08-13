package com.example.voting.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "STUDENT")
public class Students {

	@Id
	@SequenceGenerator(name = "STUDENT_seq", sequenceName = "STUDENT_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_seq")
	@Column(name = "STUDENT_ID", unique = true, nullable = true)
	private Long id;
	
	@NonNull
	private String studentId;

	@NonNull
	private String fullName;

	@NonNull
	private String gender;

	@NonNull
	private String username;

	@NonNull
    private String password;
    
    @NonNull
    private Long s_year;

	public Students() {
	}

	public Students(String fullName, String studentId, String gender, String username, String password, Long s_year) {
		this.fullName = fullName;
		this.username = username;
		this.gender = gender;
		this.password = password;
        this.studentId = studentId;
        this.s_year = s_year;
	}
}