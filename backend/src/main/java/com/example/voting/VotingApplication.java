package com.example.voting;

import com.example.voting.entity.*;

import com.example.voting.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Stream;
import com.example.voting.storage.*;
import com.example.voting.storage.StorageProperties;
import com.example.voting.service.*;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class VotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

	@Bean
	ApplicationRunner init(AdminRepository adminRepository, CandidateProfileRepository candidateProfileRepository,
			GenderRepository genderRepository, MajorRepository majorRepository, StudentsRepository studentsRepository,
			VoteRepository voteRepository, TimeManagementRepository timeManagementRepository) {
		return args -> {

			// Students
			Students student1 = new Students("นายภุมรินทร์", "b6003296", "ชาย", "0621983565", "1480000330642", "2",
					"hantermaster0@gmail.com", "ComputerEngineer");
			Students student2 = new Students("นายพรเทพ", "b6003235", "ชาย", "0625687511", "1489750329658", "2",
					"Pontep@gmail.com", "ComputerEngineer");
			Students student3 = new Students("นางสาวกมลฉัตร", "b6000358", "หญิง", "0621983565", "1480010329569", "2",
					"Kamolchat@gmail.com", "ComputerEngineer");
			Students student4 = new Students("นายมโน เอาเอง", "B6001234", "ชาย", "0960032169", "1480010329785", "2",
					"Mahno@gmail.com", "ComputerEngineer");
			Students student5 = new Students("นางสาวทดสอบ ระบบ", "B6008376", "หญิง", "0960032169", "0000", "3",
					"Mahno@gmail.com", "ComputerEngineer");
			Students student6 = new Students("นายจงรัก ภักดี", "B6201234", "ชาย", "0960032169", "0000", "2",
					"Mahno@gmail.com", "ComputerEngineer");

			Stream.of(student1, student2, student3, student4, student5, student6).forEach(student -> {
				studentsRepository.save(student);
			});

			// Admins
			Admin admin1 = new Admin("ลีโอ", "admin1", "1234", "ชาย");
			Admin admin2 = new Admin("กมลฉัตร", "admin2", "1234", "หญิง");

			Stream.of(admin1, admin2).forEach(admin -> {
				adminRepository.save(admin);
			});

			// Gender
			Gender g1 = new Gender("ชาย");
			Gender g2 = new Gender("หญิง");

			Stream.of(g1, g2).forEach(gd -> {
				genderRepository.save(gd);
			});

			// Major
			Major m1 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาเคมี");
			Major m2 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาคณิตศาสตร์");
			Major m3 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาชีววิทยา");
			Major m4 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาฟิสิกส์");
			Major m5 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาการรับรู้จากระยะไกล");
			Major m6 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาเทคโนโลยีเลเซอร์และโฟตอนนิกส์");
			Major m7 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาจุลชีววิทยา");
			Major m8 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาชีวเคมี");
			Major m9 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาวิทยาศาสตร์การกีฬา");
			Major m10 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชากายวิภาคศาสตร์");
			Major m11 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาสรีรวิทยา");
			Major m12 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาปรสิตวิทยา");
			Major m13 = new Major("สำนักวิชาวิทยาสตร์ สาขาวิชาเภสัชวิทยา");
			Major m14 = new Major("สำนักวิชาเทคโนโลยีสังคม สาขาวิชาศึกษาทั่วไป");
			Major m15 = new Major("สำนักวิชาเทคโนโลยีสังคม สาขาวิชาภาษาต่างประเทศ");
			Major m16 = new Major("สำนักวิชาเทคโนโลยีสังคม สาขาวิชาเทคโนโลยีสารสนเทศ");
			Major m17 = new Major("สำนักวิชาเทคโนโลยีสังคม สาขาวิชาเทคโนโลยีการจัดการ");
			Major m18 = new Major("สำนักวิชาเทคโนโลยีการเกษตร สาขาวิชาเทคโนโลยีการผลิตพืช");
			Major m19 = new Major("สำนักวิชาเทคโนโลยีการเกษตร สาขาวิชาเทคโนโลยีการผลิตสัตว์");
			Major m20 = new Major("สำนักวิชาเทคโนโลยีการเกษตร สาขาวิชาเทคโนโลยีชีวภาพ");
			Major m21 = new Major("สำนักวิชาเทคโนโลยีการเกษตร สาขาวิชาเทคโนโลยีอาหาร");
			Major m22 = new Major("สำนักวิชาสาธารณสุขศาสตร์ สาขาวิชาอาชีวอนามัยและความปลอดภัย");
			Major m23 = new Major("สำนักวิชาสาธารณสุขศาสตร์ สาขาวิชาอนามัยสิ่งแวดล้อม");
			Major m24 = new Major("สำนักวิชาแพทยศาสตร์ แพทยศาสตร์");
			Major m25 = new Major("สำนักวิชาพยาบาลศาสตร์");
			Major m26 = new Major("สำนักวิชาทันตแพทยศาสตร์");
			Major m27 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมการผลิต");
			Major m28 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมเกษตร");
			Major m29 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมขนส่ง");
			Major m30 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมคอมพิวเตอร์");
			Major m31 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมเคมี");
			Major m32 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมเครื่องกล");
			Major m33 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมเซรามิก");
			Major m34 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมโทรคมนาคม");
			Major m35 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมพอลิเมอร์");
			Major m36 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมไฟฟ้า");
			Major m37 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมโยธา");
			Major m38 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมโลหการ");
			Major m39 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมสิ่งแวดล้อม");
			Major m40 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมอุตสาหการ");
			Major m41 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมธรณี");
			Major m42 = new Major("สำนักวิชาวิศวกรรมศาสตร์ สาขาวิชาวิศกรรมอิเล็กทรอนิกส์");
			Stream.of(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21,
					m22, m23, m24, m25, m26, m27, m28, m29, m30, m31, m32, m33, m34, m35, m36, m37, m38, m39, m40, m41,
					m42).forEach(m -> {
						majorRepository.save(m);
					});

			String selectDate = "1998-08-11";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate birthday = LocalDate.parse(selectDate, formatter);

			String date1 = "1999-02-01";
			LocalDate birthday1 = LocalDate.parse(date1, formatter);
			// CandidateProfile
			// 1
			CandidateProfile cp1 = new CandidateProfile();
			cp1.setTitle_name("นาย");
			cp1.setC_name("จงรัก ภักดี");
			cp1.setBirthday(birthday);
			cp1.setTelephone("0960032169");
			cp1.setStudentId("B6201234");
			cp1.setYear(2);
			cp1.setGrade("3.45");
			cp1.setArchivement("โครงการ Green and Clean, บริจาคโลหิต");
			cp1.setPurpose("อยากมีส่วนร่วมกิจกรรมของมหาวิทยาลัย");
			cp1.setC_number(201);
			cp1.setAvatar("test02.jpg");
			cp1.setMajor(m36);
			cp1.setGender(g1);
			cp1.setAdmin(admin2);
			cp1.setPdf("testfile.pdf");

			// 2
			CandidateProfile cp2 = new CandidateProfile();
			cp2.setTitle_name("นางสาว");
			cp2.setC_name("ใจดี มีเมตตา");
			cp2.setBirthday(birthday);
			cp2.setTelephone("0981135169");
			cp2.setStudentId("B6204321");
			cp2.setYear(2);
			cp2.setGrade("3.65");
			cp2.setArchivement("บริจาคโลหิต");
			cp2.setPurpose("อยากมีส่วนร่วมกิจกรรมของมหาวิทยาลัย");
			cp2.setC_number(202);
			cp2.setAvatar("test01.png");
			cp2.setMajor(m17);
			cp2.setGender(g2);
			cp2.setAdmin(admin2);
			cp2.setPdf("testfile.pdf");

			CandidateProfile cp3 = new CandidateProfile();
			cp3.setTitle_name("นาย");
			cp3.setBirthday(birthday1);
			cp3.setStudentId("B6204321");
			cp3.setC_name("คริสโตเฟอร์ แซ่จีน");
			cp3.setYear(2);
			cp3.setTelephone("0981135169");
			cp3.setGrade("4.00");
			cp3.setArchivement("บริจาคโลหิต");
			cp3.setPurpose("อยากมีส่วนร่วมกิจกรรมของมหาวิทยาลัย");
			cp3.setC_number(203);
			cp3.setAvatar("test03.jpg");
			cp3.setMajor(m17);
			cp3.setGender(g1);
			cp3.setAdmin(admin2);
			cp3.setPdf("testfile.pdf");

			CandidateProfile cp4 = new CandidateProfile();
			cp4.setTitle_name("นาย");
			cp4.setBirthday(birthday1);
			cp4.setStudentId("B6204321");
			cp4.setC_name("จอห์น นี่");
			cp4.setYear(3);
			cp4.setTelephone("0981135169");
			cp4.setGrade("3.99");
			cp4.setArchivement("บริจาคโลหิต");
			cp4.setPurpose("อยากมีส่วนร่วมกิจกรรมของมหาวิทยาลัย");
			cp4.setC_number(301);
			cp4.setAvatar("test04.jpg");
			cp4.setMajor(m17);
			cp4.setGender(g1);
			cp4.setAdmin(admin2);
			cp4.setPdf("testfile.pdf");

		
			Stream.of(cp1, cp2, cp3, cp4).forEach(cp -> {
				candidateProfileRepository.save(cp);

			});
			// Vote v1 = new Vote();
			// v1.setHash("asdasdwqdq");
			// v1.setPrevHash("ertyuiop");
			// v1.setCandidateProfile(cp1);
			// v1.setStudents(student1);
			// v1.setVoteTime(new Date());
			//
			// Vote v2 = new Vote();
			// v2.setHash("qwertyu");
			// v2.setPrevHash("ertyuioppkm");
			// v2.setCandidateProfile(cp1);
			// v2.setStudents(student2);
			// v2.setVoteTime(new Date());
			//
			// Vote v3 = new Vote();
			// v3.setHash("asdasdwqdq");
			// v3.setPrevHash("ertyuiop");
			// v3.setCandidateProfile(cp2);
			// v3.setStudents(student3);
			// v3.setVoteTime(new Date());
			//
			// Stream.of(v1,v2,v3).forEach(vote -> {
			// voteRepository.save(vote);
			// });

				//วันเวลาเลือกตั้ง

			String select_time_start = "10:30";
			String select_time_end = "11:30";
			String select_election_day = "2021-03-12";
			
			DateTimeFormatter date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate election_day = LocalDate.parse(select_election_day, date_format);

			DateTimeFormatter time_format = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time_start = LocalTime.parse(select_time_start, time_format);
            LocalTime time_end = LocalTime.parse(select_time_end, time_format);
			
			TimeManagement t1 = new TimeManagement();
			t1.setElection_day(election_day);
			t1.setTime_start(time_start);
			t1.setTime_end(time_end);
			t1.setAdmin(admin2);

			Stream.of(t1).forEach(time -> {
				timeManagementRepository.save(time);

			});

		};
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	@Bean
	CommandLineRunner init(PdfStorage pdfStorage) {
		return (args) -> {
			pdfStorage.deleteAll();
			pdfStorage.init();
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