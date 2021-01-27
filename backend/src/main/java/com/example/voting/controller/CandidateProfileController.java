package com.example.voting.controller;

import com.example.voting.storage.*;

import com.example.voting.service.CandidateProfileService;
import com.example.voting.entity.*;
import com.example.voting.entity.payload.CandidateProfilePayload;
import com.example.voting.entity.payload.FindCandidate;
import com.example.voting.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CandidateProfileController {

    @Autowired
    private CandidateProfileRepository candidateProfileRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    StorageService storageService;

    @Autowired
    CandidateProfileService candidateProfileService;

    public CandidateProfileController(CandidateProfileRepository candidateProfileRepository) {
        this.candidateProfileRepository = candidateProfileRepository;
    }

    @GetMapping("/canprofile")
    public Collection<CandidateProfile> getAllCandidateProfile() {
        return candidateProfileRepository.findAll().stream().collect(Collectors.toList());
    }

    @DeleteMapping("/deletecanprofile/{id}")
    public String deleteCandidate(@PathVariable(value = "id") Long can_id) {
       return candidateProfileService.deleteById(can_id);
    }

    @PutMapping("/editcanprofile")
    public String editCandidate(@RequestBody CandidateProfile request) {
       return candidateProfileService.editCandidate(request);
    }

    // @GetMapping("/canprofile/searchC_name={c_name}")
    //     Optional<CandidateProfile> getDetialCandidate(@PathVariable String c_name) {
    //     Optional<CandidateProfile> can1 = candidateProfileRepository.findByC_name(c_name);
    //         return can1;    
    // }

    @GetMapping("/canprofile2")
    public Collection<CandidateProfile> getAllCandidateProfileByYear(@RequestParam int year) {
        return candidateProfileRepository.findByYear(year);
    }

    @PostMapping("/canp/student")
    public ResponseEntity<?> findCanwherestudent(@RequestBody FindCandidate payload) {
        Optional<CandidateProfile> candidateProfile = candidateProfileRepository.findByStudentId(payload.getStudentId());
        if (candidateProfile.isPresent()) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.ok().body(false);
        }
//        return ResponseEntity.badRequest().body("Error: Incorrect Student_Id!");
    }

    @PostMapping("/canp")
    public ResponseEntity<?> newCandidateProfile(@RequestParam("title_name") String title_name,
                                                 @RequestParam("canp_name") String c_name,
                                                 @RequestParam("birthday") String selectDate,
                                                 @RequestParam("telephone") String telephone,
                                                 @RequestParam("student_id") String student_id,
                                                 @RequestParam("year") int year,
                                                 @RequestParam("grade") String grade,
                                                 @RequestParam("archivement") String archivement,
                                                 @RequestParam("c_number") int c_number,
                                                 @RequestParam("purpose") String purpose,
                                                 @RequestParam("major") long major_id,
                                                 @RequestParam("gender") long gender_id,
                                                 @RequestParam("admin") long admin_id,
                                                 @RequestParam("file") MultipartFile file) {
        try {

            //1> Try to save data to database.
            CandidateProfile cp = new CandidateProfile();
            Optional<Major> major = majorRepository.findById(major_id);
            Optional<Gender> gender = genderRepository.findById(gender_id);
            Optional<Admin> admin = adminRepository.findById(admin_id);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthday = LocalDate.parse(selectDate, formatter);
//            Set field
            cp.setTitle_name(title_name);
            cp.setC_name(c_name);
            cp.setBirthday(birthday);
            cp.setTelephone(telephone);
            cp.setStudentId(student_id);
            cp.setYear(year);
            cp.setGrade(grade);
            cp.setArchivement(archivement);
            cp.setC_number(c_number);
            cp.setPurpose(purpose);
            cp.setAvatar(file.getOriginalFilename()); //b6000.jpg
            cp.setMajor(major.get());
            cp.setGender(gender.get());
            cp.setAdmin(admin.get());
//            Save
            candidateProfileRepository.saveAndFlush(cp);

//            2> Try to Save File to server.
            storageService.store(file);
//            3> Response to client
            String result = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Error when try to save image! -> " + e.getMessage());
        }

    }

}