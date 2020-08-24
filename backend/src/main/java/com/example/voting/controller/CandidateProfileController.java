package com.example.voting.controller;

import com.example.voting.entity.Party;
import com.example.voting.entity.Gender;
import com.example.voting.entity.Admins;
import com.example.voting.entity.CandidateProfile;

import com.example.voting.repository.PartyRepository;
import com.example.voting.repository.GenderRepository;
import com.example.voting.repository.AdminsRepository;
import com.example.voting.repository.CandidateProfileRepository;

import com.example.voting.entity.payload.VotePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import java.text.ParseException;

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
    private PartyRepository partyRepository;

    public CandidateProfileController(CandidateProfileRepository candidateProfileRepository) {
        this.candidateProfileRepository = candidateProfileRepository;
    }

    @GetMapping("/canprofile")
    public Collection<CandidateProfile> CandidateProfile() {
        return candidateProfileRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/canp")
    public CandidateProfile newCandidateProfile(@RequestBody CandidateProfilePayload canp){

        CandidateProfile cp = new CandidateProfile();

        Optional<Party> party = partyRepository.findById(canp.getParty_id());
        Optional<Gender> gender = genderRepository.findById(canp.getGender_id());
        Optional<Admins> admins = adminsRepository.findById(canp.getAdmins_id());
        
        cp.setTitle_name(cp.getTitle_name());
        cp.setC_name(cp.getC_name());
        cp.setStudent_id(cp.getStudent_id());
        cp.setMajor(cp.getMajor());
        cp.setYear(cp.getYear());
        cp.setGrade(cp.getYear());
        cp.setArchivement(cp.getArchivement());
        cp.setPosition(cp.getPosition());
        
        cp.setGender(gender.get());
        cp.setAdmins(admins.get());
        return candidateProfileRepository.save(cp);
    }

}