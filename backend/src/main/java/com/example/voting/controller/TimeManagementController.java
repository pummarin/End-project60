package com.example.voting.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.Collectors;

import com.example.voting.entity.*;
import com.example.voting.repository.*;
import com.example.voting.service.TimeManagementService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TimeManagementController {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private TimeManagementRepository timeManagementRepository;

    @Autowired
    TimeManagementService timeManagementService;

    public TimeManagementController(TimeManagementRepository timeManagementRepository) {
        this.timeManagementRepository = timeManagementRepository;
    }
    
    @GetMapping("/timeshow")
    public Collection<TimeManagement> getAllTime() {
        return timeManagementRepository.findAll().stream().collect(Collectors.toList());
    }
    // @PutMapping("/editdate")
    // public String editTime(@RequestBody TimeManagement request) {
    //     return timeManagementService.editTime(request);
    // }

    @PostMapping("/timecheck")
    public ResponseEntity<?> newTimeSet(@RequestParam(value = "election_day") String select_election_day,
                                        @RequestParam(value = "time_start") String select_time_start,
                                        @RequestParam(value = "time_end") String select_time_end,
                                        @RequestParam("admin") long admin_id) {
        try {   

   
                TimeManagement time = new TimeManagement();

                Optional<Admin> admin = adminRepository.findById(admin_id);

                DateTimeFormatter date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate election_day = LocalDate.parse(select_election_day, date_format);

                DateTimeFormatter time_format = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime time_start = LocalTime.parse(select_time_start, time_format);
                LocalTime time_end = LocalTime.parse(select_time_end, time_format);

                time.setElection_day(election_day);
                time.setTime_start(time_start);
                time.setTime_end(time_end);
                time.setAdmin(admin.get());

                timeManagementRepository.saveAndFlush(time);

                String returnTime = "set time successfully";

                     return ResponseEntity.ok().body(returnTime);
    

        } catch (Exception e) {
            System.out.println(e.getMessage());
                return ResponseEntity.badRequest().body("can't set the time" + e.getMessage());
        }
    }

    // @PostMapping("/timecheck/{select_time_start}/{select_time_end}/{admin_id}")
    // public void setTimeManagement(@PathVariable(value = "time_start") String select_time_start,
    //                               @PathVariable(value = "time_end") String select_time_end, 
    //                               @PathVariable(value = "admin") Long admin_id) {
        

    //         TimeManagement time = new TimeManagement();

    //         Optional<Admin> admin = adminRepository.findById(admin_id);
    //         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //         LocalDateTime time_start = LocalDateTime.parse(select_time_start, formatter);
    //         LocalDateTime time_end = LocalDateTime.parse(select_time_end, formatter);

    //         time.setTime_start(time_start);
    //         time.setTime_end(time_end);
    //         time.setAdmin(admin.get());

    //         timeManagementRepository.saveAndFlush(time);


    // }
}
