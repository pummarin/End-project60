package com.example.voting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;


@Data
@Entity
@Table(name = "ADMIN")
public class Admins {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADMIN_ID", insertable = true)
    private long id;

    private @NonNull String name;
    private @NonNull String username;
    private @NonNull String password;
    private @NonNull String gender;


    public Admins() {
    }

    public Admins(String name, String user, String pass, String gender) {
        this.name = name;
        this.password = pass;
        this.username = user;
        this.gender = gender;
    }
}