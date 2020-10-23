package com.example.voting.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.voting.entity.CandidateProfile;
import com.example.voting.entity.Students;
import com.example.voting.entity.Vote;

import com.example.voting.entity.payload.FindStudent;
import com.example.voting.entity.payload.VotePayload;
import com.example.voting.repository.CandidateProfileRepository;
import com.example.voting.repository.StudentsRepository;
import com.example.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class VoteController {
    
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    StudentsRepository studentsRepository;

    @Autowired
    CandidateProfileRepository candidateProfileRepository;


    @GetMapping("/votes")
    public Collection<Vote> getAllVote() {
        return voteRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/vote/new")
    public ResponseEntity<?> newVote2(@RequestBody VotePayload v){
        //verify payload

        Vote newVote = new Vote();
        Optional<Students> students = studentsRepository.findById(v.getStudents_id());
        Optional<CandidateProfile> candidateProfile = candidateProfileRepository.findById(v.getCan_id());

        newVote.setVoteTime(new Date());


        if (!students.isPresent()){
            return ResponseEntity.badRequest().body("studentsId is not correct!!");
        }
        newVote.setStudents(students.get());

        if (!candidateProfile.isPresent()){
            return ResponseEntity.badRequest().body("candidateprofileId is not correct!!");
        }
        newVote.setCandidateProfile(candidateProfile.get());

//        todo import hash function

//        todo getPrevHash from lasted record and set it
        Optional<Vote> prevVote = voteRepository.findTopByOrderByIdDesc();
        if (prevVote.isPresent()){
            String prevHash = prevVote.get().getHash();
            newVote.setPrevHash(prevHash);
        }else {
            //todo hash it's self
            try{
                newVote = voteRepository.saveAndFlush(newVote);
                String data = newVote.toString();
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
                String encoded = DatatypeConverter.printHexBinary(hash);
//                System.out.println(encoded.toLowerCase());
                newVote.setPrevHash(encoded.toLowerCase());
            }catch (Exception e){
                System.out.println(e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
//      todo hash it's self
        try{
            newVote = voteRepository.saveAndFlush(newVote);
            String data1 = newVote.toString();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data1.getBytes(StandardCharsets.UTF_8));
            String encoded1 = DatatypeConverter.printHexBinary(hash);
//                System.out.println(encoded.toLowerCase());
            newVote.setHash(encoded1.toLowerCase());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

         voteRepository.save(newVote);
        return ResponseEntity.ok().body("vote sucessfully");
    }


    @PostMapping("/vote/student")
    public ResponseEntity<?> findVoteWhereStudent(@RequestBody FindStudent payload){
        Optional<Students> student = studentsRepository.findById(payload.getStudent_id());
        if(student.isPresent()){
            Optional<Vote> vote = voteRepository.findByStudents(student.get());
            if(vote.isPresent()) {
                return ResponseEntity.ok().body(true);
            }else {
                return ResponseEntity.ok().body(false);
            }
        }
        return ResponseEntity.badRequest().body("Error: Incorrect Student_Id!");
    }

}