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
public class PartyController {

    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private CandidateProfileRepository candidateProfileRepository;
    
    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping("/party")
    public Collection<PartyRepository> PartyRepository() {
        return partyRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/party/{p_name}/{member}/{policy}/{p_number}")
    public void newParty(
        
                        
                        @PathVariable long party_id,
                        @PathVariable String p_name,
                        @PathVariable Integer member,
                        @PathVariable String policy,
                        @PathVariable Integer p_number,
                        ) 
                  {
                
                Party party = new Party();
                party.setName(p_name);
                party.setMember(member);
                party.setPolicy(policy);
                party.setPartyNo(p_number);

                partyRepository.save(party);
    }
    
}