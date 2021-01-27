package com.example.voting.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.example.voting.entity.CandidateProfile;
import com.example.voting.entity.CandidateScoreResponeModel;
import com.example.voting.entity.Students;
import com.example.voting.entity.Vote;

import com.example.voting.entity.payload.FindStudent;
import com.example.voting.entity.payload.VotePayload;
import com.example.voting.model.CheckedAllBoxRespone;
import com.example.voting.repository.CandidateProfileRepository;
import com.example.voting.repository.StudentsRepository;
import com.example.voting.repository.VoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
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

    @GetMapping("/vote/result")
    public ResponseEntity<?> getVoteResult() {
        // todo count point from each number
        // todo get candidate
        List<CandidateProfile> candidateProfiles = candidateProfileRepository.findAll();
        JSONObject cps[] = new JSONObject[candidateProfiles.size()];
        // todo get vote
        List<Vote> votes = voteRepository.findAll();
        // todo check candidate in vote table and count score

        votes.stream().forEach(vote -> {
//            candidateProfiles
//            cps[vote.getCandidateProfile().getCan_id().intValue()].put("")
//            candidateProfiles.get
            if (candidateProfiles.contains(vote.getCandidateProfile())) {
                int index = candidateProfiles.indexOf(vote.getCandidateProfile());
                candidateProfiles.get(index);

            }
        });
        return ResponseEntity.ok().body("e.getMessage()");
    }

    @PostMapping("/vote/new")
    public ResponseEntity<?> newVote2(@RequestBody VotePayload v) {
        //verify payload

        Vote newVote = new Vote();
        Optional<Students> students = studentsRepository.findById(v.getStudents_id());
        Optional<CandidateProfile> candidateProfile = candidateProfileRepository.findById(v.getCan_id());

        newVote.setVoteTime(LocalDateTime.now());


        if (!students.isPresent()) {
            return ResponseEntity.badRequest().body("studentsId is not correct!!");
        } else if (voteRepository.findByStudents(students.get()).isPresent()) {
            //      todo check student vote?
            return ResponseEntity.badRequest().body("This student have been voted");
        }

        newVote.setStudents(students.get());
        ObjectMapper mapper = new ObjectMapper();
        if (!candidateProfile.isPresent()) {
            return ResponseEntity.badRequest().body("candidateprofileId is not correct!!");
        }
        newVote.setCandidateProfile(candidateProfile.get());
        try {
            System.out.println("candidateprofile " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(candidateProfile.get()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


//        todo import hash function

//        todo getPrevHash from lasted record and set it
        Optional<Vote> prevVote = voteRepository.findTopByOrderByIdDesc();
        if (prevVote.isPresent()) {
            String prevHash = prevVote.get().getHash();
            newVote.setPrevHash(prevHash);
        } else {
            //todo hash it's self
            try {
//                System.out.println("before xxxxx" + newVote.toString());
                newVote = voteRepository.saveAndFlush(newVote);
//                System.out.println("after" + newVote);
                String data =mapper.writeValueAsString(newVote) ;
//                System.out.println("origin xxxx" + data);
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
                String encoded = DatatypeConverter.printHexBinary(hash);
//                System.out.println(encoded.toLowerCase());
                newVote.setPrevHash(encoded.toLowerCase());
//                System.out.println("vote1 = "+newVote);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
//      todo hash it's self
        try {
            newVote = voteRepository.saveAndFlush(newVote);
//            System.out.println("before " + newVote);
            String data1 =mapper.writeValueAsString(newVote) ;
//            System.out.println("data1 = " + data1);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data1.getBytes(StandardCharsets.UTF_8));
            String encoded1 = DatatypeConverter.printHexBinary(hash);
//                System.out.println(encoded.toLowerCase());
            System.out.println("encode1 = "+encoded1.toLowerCase());
            newVote.setHash(encoded1.toLowerCase());
            System.out.println("vote2 = "+ newVote);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        voteRepository.save(newVote);
//        System.out.println("save new vote = " + newVote);
        return ResponseEntity.ok().body("vote sucessfully");
    }

    @GetMapping("/vote/getCheckedAllBox")
    public ResponseEntity<?> getCheckedAllBox() {
        List<Vote> votes = voteRepository.findAll();
//        System.out.println("get all vote = " + votes);
        ArrayList<CheckedAllBoxRespone> respone = new ArrayList<CheckedAllBoxRespone>();
        AtomicReference<String> tempPrevHash = new AtomicReference<>("");
        votes.forEach(vote -> {
            CheckedAllBoxRespone c = new CheckedAllBoxRespone();
            c.setId(vote.getId());
            c.setHash(vote.getHash());
            c.setPrevHash(vote.getPrevHash());
//            System.out.println("c = "+c);
//            System.out.println("sssss = " + vote);
            if (checkHashData(vote, tempPrevHash.get())) {
                c.setCorrect(true);
            } else {
                c.setCorrect(false);
            }
            tempPrevHash.set(vote.getHash());
            respone.add(c);
        });
        return ResponseEntity.ok().body(respone);
    }

    public Boolean checkHashData(Vote vote, String prevHash) {
        System.out.println("origin vote" + vote);
//        Date d1 = new Date(vote.getVoteTime());
        String hash = vote.getHash();
        String prevhash = vote.getPrevHash();

        ObjectMapper mapper = new ObjectMapper();

        try {
            //เช้คค่าprevhashเดิมกับใหม่ว่าตรงกันไหม
            if (prevHash.equals("")) {
                vote.setHash(null);
                vote.setPrevHash(null);
                String data = mapper.writeValueAsString(vote) ;
//                System.out.println("data = " + data);
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash1 = digest.digest(data.getBytes(StandardCharsets.UTF_8));
                String encoded = DatatypeConverter.printHexBinary(hash1);
                vote.setPrevHash(encoded.toLowerCase());
                System.out.println("vote set prevhash " + encoded.toLowerCase());
                if (!vote.getPrevHash().equals(prevhash) ) {
                    System.out.printf("prevhashincorrect case 1st box " + vote.getPrevHash() + "!=" + prevhash + "\n");
                    return false;
                }
            } else {
                if (!vote.getPrevHash().equals(prevhash)) {
                    System.out.printf("prevhashincorrect case nomal " + vote.getPrevHash() + "!=" + prevhash + "\n");
                    return false;
                }
            }
            vote.setHash(null);
            String data = mapper.writeValueAsString(vote);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash1 = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            String encoded = DatatypeConverter.printHexBinary(hash1);
            //เช็คค่าhashว่าตรงกันไหม
            vote.setHash(encoded.toLowerCase());
            if (!vote.getHash().equals(hash) ) {
                System.out.printf(vote.getHash() + "!=" + vote.getHash());
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }


    @PostMapping("/vote/student")
    public ResponseEntity<?> findVoteWhereStudent(@RequestBody FindStudent payload) {
        Optional<Students> student = studentsRepository.findById(payload.getStudent_id());
        if (student.isPresent()) {
            Optional<Vote> vote = voteRepository.findByStudents(student.get());
            if (vote.isPresent()) {
                return ResponseEntity.ok().body(true);
            } else {
                return ResponseEntity.ok().body(false);
            }
        }
        return ResponseEntity.badRequest().body("Error: Incorrect Student_Id!");
    }

    @GetMapping("/score")
    public ResponseEntity<?> getAllCandidateScore() {
        List<Vote> votes = voteRepository.findAll();
        List<CandidateProfile> candidateProfiles = candidateProfileRepository.findAll();
        ArrayList<CandidateScoreResponeModel> candidateScoreResponeModel = new ArrayList<CandidateScoreResponeModel>();
        //เบอร์ที่ไม่มาเพราะ ไม่มีใครโหวต
        candidateProfiles.forEach(vote -> {
            CandidateScoreResponeModel temp = new CandidateScoreResponeModel();
            temp.setCandidateProfile(vote);
            if (!candidateScoreResponeModel.contains(temp))
                candidateScoreResponeModel.add(temp);
        });

        votes.forEach(vote -> {
            CandidateScoreResponeModel temp = new CandidateScoreResponeModel();
            temp.setCandidateProfile(vote.getCandidateProfile());
            AtomicInteger i = new AtomicInteger();
            candidateScoreResponeModel.forEach(c -> {
                if (c.getCandidateProfile() == temp.getCandidateProfile()) {
                    int j = candidateScoreResponeModel.indexOf(c);
                    i.set(j);
                }
            });
            //นับคะแนน
            candidateScoreResponeModel.get(i.get()).setScore(candidateScoreResponeModel.get(i.get()).getScore() + 1);
        });
        return ResponseEntity.ok().body(candidateScoreResponeModel);
    }
}