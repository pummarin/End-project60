package com.example.voting.repository;

import java.util.Optional;

import com.example.voting.entity.TimeManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TimeManagementRepository extends JpaRepository<TimeManagement, Long> {
    Optional<TimeManagement> findById(Long id);
}
