package com.example.voting.controller;


import com.example.voting.entity.Gender;
import com.example.voting.entity.Major;
import com.example.voting.entity.Admins;
import com.example.voting.entity.CandidateProfile;
import com.example.voting.entity.payload.CandidateProfilePayload;
import com.example.voting.repository.GenderRepository;
import com.example.voting.repository.MajorRepository;
import com.example.voting.repository.AdminsRepository;
import com.example.voting.repository.CandidateProfileRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


import java.util.Optional;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

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
    AdminsRepository adminsRepository;

    public CandidateProfileController(CandidateProfileRepository candidateProfileRepository) {
        this.candidateProfileRepository = candidateProfileRepository;
    }

    @GetMapping("/canprofile")
    public Collection<CandidateProfile> getAllCandidateProfile() {
        return candidateProfileRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/canp")
    public CandidateProfile newCandidateProfile(@RequestBody CandidateProfilePayload canp){

        CandidateProfile cp = new CandidateProfile();

        
        Optional<Major> major = majorRepository.findById(canp.getMajor_id());
        Optional<Gender> gender = genderRepository.findById(canp.getGender_id());
        Optional<Admins> admins = adminsRepository.findById(canp.getAdmins_id());
        
        cp.setTitle_name(canp.getTitle_name());
        cp.setC_name(canp.getC_name());
        cp.setBirthday(canp.getBirthday());
        cp.setTelephone(canp.getTelephone());
        cp.setStudent_id(canp.getStudent_id());
        cp.setYear(canp.getYear());
        cp.setGrade(canp.getGrade());
        cp.setArchivement(canp.getArchivement());
        cp.setC_number(canp.getC_number());
        cp.setPurpose(canp.getPurpose());
        
        cp.setMajor(major.get());
        cp.setGender(gender.get());
        cp.setAdmins(admins.get());
        return candidateProfileRepository.save(cp);
    }

}