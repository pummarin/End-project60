package com.example.voting.repository;

import com.example.voting.entity.Points;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PointsRepository extends JpaRepository<Points, Long> {
    Points findById(long id);
}
