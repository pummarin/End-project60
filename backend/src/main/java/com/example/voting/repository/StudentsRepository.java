package com.example.voting.repository;

import com.example.voting.entity.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentsRepository extends JpaRepository<Students, Long>{

    Students findById(long id);
}