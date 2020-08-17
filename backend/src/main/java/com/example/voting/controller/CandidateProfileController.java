package com.example.voting.controller;

import com.example.voting.Entity.*;
import com.example.voting.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CandidateProfileController {

    @Autowired
    private CandidateProfileRepository candidateProfileRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private PartyRepository partyRepository;

    public CandidateProfileController(CandidateProfileRepository candidateProfileRepository) {
        this.candidateProfileRepository = candidateProfileRepository;
    }

    @GetMapping("/canprofile")
    public Collection<CandidateProfile> CandidateProfile() {
        return candidateProfileRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/canprofile/{p_numberSelect}/{title_name}/{c_name}/{genSelect}/{age}/{grade}/{skill}/{status}")
    public void newCandidateProfile(
        
                        @PathVariable String title_name,
                        @PathVariable Integer p_numberSelect,
                        @PathVariable String c_name,
                        @PathVariable Integer age,
                        @PathVariable float grade,
                        @PathVariable String skill,
                        @PathVariable String status,
                        @PathVariable long genSelect,
                        ) 
                  {

                Party party = partyRepository.findById(p_numberSelect);
                Gender gender = genderRepository.findById(genSelect);
                
                CandidateProfile c = new CandidateProfile();
                c.setTitle_name(title_name);
                c.setName(c_name);
                c.setAge(age);
                c.setGrade(grade);
                c.setSkill(skill);
                c.setStatus(status);
                c.setParty(p_numberSelect);

                c.save(candidateProfile);
    }
}