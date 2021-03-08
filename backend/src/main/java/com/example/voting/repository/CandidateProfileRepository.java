package com.example.voting.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

import com.example.voting.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Long>{
    Optional<CandidateProfile> findById(Long can_id);
    Collection<CandidateProfile> findByYear(int year);
    Optional<CandidateProfile> findByStudentId(String studentId);
  
}