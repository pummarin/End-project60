package com.example.voting;

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
			Students student1 = new Students("นายภุมรินทร์ เพียวสูงเนิน", "b6003296", "ชาย",
					"0621983565","1479900329542","4","hantermaster0@gmail.com", "computer");

			Stream.of(student1).forEach(student -> {
				studentsRepository.save(student);
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
