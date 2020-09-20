package com.example.voting.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.voting.entity.Students;
import com.example.voting.entity.Vote;

import com.example.voting.entity.payload.VotePayload;
import com.example.voting.repository.StudentsRepository;
import com.example.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class VoteController {
    
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    StudentsRepository studentsRepository;




    @GetMapping("/votes")
    public Collection<Vote> getAllVote() {
        return voteRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/vote/new")
    public Vote newVote2(@RequestBody VotePayload v){
        Vote newVote = new Vote();
        Optional<Students> students = studentsRepository.findById(v.getStudents_id());

        newVote.setVoteTime(new Date());
        newVote.setStudents(students.get());

        return voteRepository.save(newVote);
    }
}