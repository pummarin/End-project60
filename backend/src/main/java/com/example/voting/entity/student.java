package com.example.voting.entity;

import lombok.Data;

@Data
@Entity
@Table(name = "STUDENTS")
public class Students {

	@Id
	@SequenceGenerator(name = "STUDENTS_seq", sequenceName = "STUDENTS_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENTS_seq")
	@Column(name = "STUDENTS_ID", unique = true, nullable = true)
	private Long id;
	
	@NotNull
	private String studentId;

	@NotNull
	private String fullName;

	@NotNull
	private String gender;

	@NotNull
	private String username;

	@NotNull
    private String password;
    
    @NotNull
    private Long s_year

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