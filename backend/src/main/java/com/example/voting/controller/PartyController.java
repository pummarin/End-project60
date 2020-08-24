package com.example.voting.controller;


import com.example.voting.entity.Party;
import com.example.voting.entity.Admins;

import com.example.voting.repository.PartyRepository;
import com.example.voting.repository.AdminsRepository;

import com.example.voting.entity.payload.VotePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PartyController {

    @Autowired
    private AdminsRepository adminsRepository;
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private CandidateProfileRepository candidateProfileRepository;
    
    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping("/partys")
    public Collection<PartyRepository> PartyRepository() {
        return partyRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @PostMapping("/party")
    public Party newParty(@RequestBody PartyPayload p){

        Party party = new Party();

        Optional<Admins> admins = adminsRepository.findById(p.getAdmins_id());
        
        party.setP_name(p.getP_name());
        party.setPolicy(p.getPolicy());
        party.setP_number(p.getP_number());
        party.setPoint(p.getPoint());
        party.setAdmins(admins.get());

        return partyRepository.save(party);
    }
    
    
}