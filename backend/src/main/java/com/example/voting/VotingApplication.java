package com.example.voting;

import com.example.voting.entity.Admins;
import com.example.voting.entity.Students;
import com.example.voting.repository.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.stream.Stream;

@SpringBootApplication
public class VotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

	@Bean
	ApplicationRunner init(AdminsRepository adminsRepository, CandidateProfileRepository candidateProfileRepository,
						   GenderRepository genderRepository, MajorRepository majorRepository, PartyRepository partyRepository,
						   StudentsRepository studentsRepository, VoteRepository voteRepository){
		return  args -> {

			//Students
			Students student1 = new Students("นายภุมรินทร์", "b6003296", "ชาย",
					"0621983565","1480000330642","4","hantermaster0@gmail.com", "ComputerEngineer");
			Students student2 = new Students("นายพรเทพ", "b6003235", "ชาย",
					"0625687511","1489750329658","4","Pontep@gmail.com", "ComputerEngineer");
			Students student3 = new Students("นางสาวกมลฉัตร", "b6000358", "หญิง",
					"0621983565","1480010329569","4","Kamonchat@gmail.com", "ComputerEngineer");


			Stream.of(student1,student2,student3).forEach(student -> {
				studentsRepository.save(student);
			});

			//Admins
			Admins admin1 = new Admins("leo", "admin1", "1234", "ชาย");
			Admins admin2 = new Admins("fin", "admin2", "1234", "หญิง");

			Stream.of(admin1,admin2).forEach(admins -> {
				adminsRepository.save(admins);
			});
		};
	}

	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
