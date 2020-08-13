package com.example.voting.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.voting.entity.Vote;

import com.example.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class VoteController {
    
    @Autowired
    VoteRepository voteRepository;

    @GetMapping("/votes")
    public Collection<Vote> getAllVote() {
        return voteRepository.findAll().stream().collect(Collectors.toList());
    }


}