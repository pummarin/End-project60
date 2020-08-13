package com.example.voting.repository;

import com.example.voting.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AdminsRepository extends JpaRepository<Admins, Long> {
    Optional<Admins> findById(Long id);
}
