package com.example.voting;

import com.example.voting.entity.Admins;
import com.example.voting.entity.Students;
import com.example.voting.entity.Gender;
import com.example.voting.entity.Major;
import com.example.voting.entity.CandidateProfile;

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
						   GenderRepository genderRepository, MajorRepository majorRepository, StudentsRepository studentsRepository, 
						   VoteRepository voteRepository){
		return  args -> {

			//Students
			Students student1 = new Students("นายภุมรินทร์", "b6003296", "ชาย",
					"0621983565","1480000330642","4","hantermaster0@gmail.com", "ComputerEngineer");
			Students student2 = new Students("นายพรเทพ", "b6003235", "ชาย",
					"0625687511","1489750329658","4","Pontep@gmail.com", "ComputerEngineer");
			Students student3 = new Students("นางสาวกมลฉัตร", "b6000358", "หญิง",
					"0621983565","1480010329569","4","Kamolchat@gmail.com", "ComputerEngineer");


			Stream.of(student1,student2,student3).forEach(student -> {
				studentsRepository.save(student);
			});

			//Admins
			Admins admin1 = new Admins("leo", "admin1", "1234", "ชาย");
			Admins admin2 = new Admins("fin", "admin2", "1234", "หญิง");

			Stream.of(admin1,admin2).forEach(admins -> {
				adminsRepository.save(admins);
			});


			//Gender
			Gender g1 = new Gender("ชาย");
			Gender g2 = new Gender("หญิง");
			
            Stream.of(g1, g2).forEach(gd -> {
                genderRepository.save(gd);
			}); 

			//Major
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
				m22, m23, m24, m25, m26, m27, m28, m29, m30, m31, m32, m33, m34, m35, m36, m37, m38, m39, m40, m41, m42).forEach(m -> {
                majorRepository.save(m);
			});	

			//CandidateProfile
			CandidateProfile cp1 = new CandidateProfile();
			cp1.setTitle_name("นาย");
			cp1.setC_name("จงรัก ภักดี");
			cp1.setBirthday("1 มกราคม 2542");
			cp1.setTelephone("0960032169");
        	cp1.setStudent_id("B6001234");
        	cp1.setYear(2);
        	cp1.setGrade("3.45");
        	cp1.setArchivement("โครงการ Green and Clean, บริจาคโลหิต");
			cp1.setPurpose("อยากมีส่วนร่วมกิจกรรมของมหาวิทยาลัย");
			cp1.setC_number(201);
        
        	cp1.setMajor(m36);
        	cp1.setGender(g1);
        	cp1.setAdmins(admin2);

			CandidateProfile cp2 = new CandidateProfile();
			cp2.setTitle_name("นางสาว");
			cp2.setC_name("ใจดี มีเมตตา");
			cp2.setBirthday("14 กุมภาพันธ์ 2543");
			cp2.setTelephone("0981135169");
        	cp2.setStudent_id("B6104321");
        	cp2.setYear(2);
        	cp2.setGrade("3.65");
        	cp2.setArchivement("บริจาคโลหิต");
			cp2.setPurpose("อยากมีส่วนร่วมกิจกรรมของมหาวิทยาลัย");
			cp2.setC_number(202);
        
        	cp2.setMajor(m17);
        	cp2.setGender(g2);
        	cp2.setAdmins(admin2);
	
			Stream.of(cp1, cp2).forEach(cp -> {
			candidateProfileRepository.save(cp);
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
