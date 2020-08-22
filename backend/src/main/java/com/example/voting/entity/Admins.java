package com.example.voting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;


@Data
@Entity
@Table(name = "ADMIN")
public class Admins {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADMIN_ID", insertable = true)
    private long id;

    private @NotNull String name;
    private @NotNull String username;
    private @NotNull String password;
    private @NotNull String gender;


    public Admins() {
    }

    public Admins(String name, String user, String pass, String gender) {
        this.name = name;
        this.password = pass;
        this.username = user;
        this.gender = gender;
    }
}