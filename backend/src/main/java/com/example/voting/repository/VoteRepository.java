package com.example.voting.repository;

import com.example.voting.entity.Students;
import com.example.voting.entity.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VoteRepository extends JpaRepository<Vote, Long> {
	Vote findById(long id);
	Vote findByStudent(Students students);
}