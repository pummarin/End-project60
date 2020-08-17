package com.example.voting.repository;

import com.example.voting.entity.*;

@RepositoryRestResource
public class PartyRepository extends JpaRepository<Party, Long> {

}