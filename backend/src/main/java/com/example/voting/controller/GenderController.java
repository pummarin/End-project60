package com.example.voting.controller;

@RestController
public class GenderController {

    @Autowired
    private GenderRepository genderRepository;

    public GenderController(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @GetMapping("/gender")
    public Collection<Gender> Gender() {
        return genderRepository.findAll().stream().collect(Collectors.toList());
    }
}