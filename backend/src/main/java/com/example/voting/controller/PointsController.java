package com.example.voting.controller;


import com.example.voting.entity.Points;
import com.example.voting.repository.PointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PointsController {
    @Autowired
    PointsRepository pointsRepository;

    @GetMapping("/points")
    public Collection<Points> getAllPoints() {return pointsRepository.findAll().stream().collect(Collectors.toList());}
}
