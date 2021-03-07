package com.example.voting.service;

import com.example.voting.repository.TimeManagementRepository;
import com.example.voting.entity.TimeManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeManagementService {

    @Autowired
    private TimeManagementRepository timeManagementRepository;

    public List<TimeManagement> listAll() {
        return timeManagementRepository.findAll();
    }

    public String editTime(TimeManagement request) {
        Optional<TimeManagement> timeManagement = timeManagementRepository.findById(request.getTime_id());
        if (timeManagement.isPresent()) {
            TimeManagement time = timeManagement.get();
            time.setElection_day(request.getElection_day());
            time.setTime_start(request.getTime_start());
            time.setTime_end(request.getTime_end());
       
            timeManagementRepository.save(time);
            return "time was edited";
        } else {
            return "not success";
        }
    }
}
